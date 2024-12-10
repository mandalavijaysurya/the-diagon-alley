package org.theleakycauldron.diagonalley.services.implementations;

import com.fasterxml.uuid.impl.TimeBasedReorderedGenerator;
import org.springframework.stereotype.Service;
import org.theleakycauldron.diagonalley.commons.DiagonAlleyUtils;
import org.theleakycauldron.diagonalley.dtos.*;
import org.theleakycauldron.diagonalley.daos.entities.*;
import org.theleakycauldron.diagonalley.exceptions.*;
import org.theleakycauldron.diagonalley.repositories.*;
import org.theleakycauldron.diagonalley.services.DiagonAlleyService;

import java.time.LocalDateTime;
import java.util.*;

import static java.time.LocalDateTime.now;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Service
public class DiagonAlleyServiceImpl implements DiagonAlleyService {

    private final DiagonAlleyRDBProductRepository diagonAlleyRDBProductRepository;
    private final DiagonAlleyRDBCategoryRepository diagonAlleyRDBCategoryRepository;
    private final TimeBasedReorderedGenerator timeBasedReorderedGenerator;
    private final DiagonAlleyRDBOutboxRepository diagonAlleyRDBOutboxRepository;
    private final DiagonAlleyOutboxEventPublisher diagonAlleyOutboxEventPublisher;
    private final DiagonAlleyRDBManufacturerRepository  diagonAlleyRDBManufacturerRepository;
    private final DiagonAlleyRDBPriceRepository diagonAlleyRDBPriceRepository;
    private final DiagonAlleyElasticProductRepository diagonAlleyElasticProductRepository;

    public DiagonAlleyServiceImpl(
            DiagonAlleyRDBProductRepository diagonAlleyRDBProductRepository,
            DiagonAlleyRDBCategoryRepository diagonAlleyRDBCategoryRepository,
            TimeBasedReorderedGenerator timeBasedReorderedGenerator,
            DiagonAlleyRDBOutboxRepository diagonAlleyRDBOutboxRepository,
            DiagonAlleyOutboxEventPublisher diagonAlleyOutboxEventPublisher,
            DiagonAlleyRDBManufacturerRepository diagonAlleyRDBManufacturerRepository,
            DiagonAlleyRDBPriceRepository diagonAlleyRDBPriceRepository,
            DiagonAlleyElasticProductRepository diagonAlleyElasticProductRepository
    ) {
        this.diagonAlleyRDBProductRepository = diagonAlleyRDBProductRepository;
        this.diagonAlleyRDBCategoryRepository = diagonAlleyRDBCategoryRepository;
        this.timeBasedReorderedGenerator = timeBasedReorderedGenerator;
        this.diagonAlleyRDBOutboxRepository = diagonAlleyRDBOutboxRepository;
        this.diagonAlleyOutboxEventPublisher = diagonAlleyOutboxEventPublisher;
        this.diagonAlleyRDBManufacturerRepository = diagonAlleyRDBManufacturerRepository;
        this.diagonAlleyRDBPriceRepository = diagonAlleyRDBPriceRepository;
        this.diagonAlleyElasticProductRepository = diagonAlleyElasticProductRepository;
    }


    @Override
    public DiagonAlleyCreateProductResponseDTO addProduct(DiagonAlleyCreateProductRequestDTO requestDTO) throws CategoryNotFoundException, ProductAlreadyExistsException{
        String productName = requestDTO.getName();
        String category = requestDTO.getProductCategory();
        UUID productUuid = timeBasedReorderedGenerator.generate();
        UUID priceUuid = timeBasedReorderedGenerator.generate();
        UUID manufacturerUuid = timeBasedReorderedGenerator.generate();
        LocalDateTime now = now();
        Optional<Product> product = diagonAlleyRDBProductRepository.findByName(productName);
        if(product.isPresent()){
            throw new ProductAlreadyExistsException("Could not add product, product already exists");
        }
        Optional<ProductCategory> productCategory = diagonAlleyRDBCategoryRepository.findByName(category);
        if(productCategory.isEmpty()){
            throw new CategoryNotFoundException("Could not add product, category does not exist");
        }
        List<String> productTags = requestDTO.getTags();
        productTags.add(productUuid.toString());
        Product newProduct = Product.builder()
                .name(productName)
                .uuid(productUuid)
                .createdAt(now)
                .updatedAt(now)
                .description(requestDTO.getDescription())
                .imageURL(requestDTO.getImageUrl())
                .price(
                        Price.builder()
                                .uuid(priceUuid)
                                .createdAt(now)
                                .updatedAt(now)
                                .isDeleted(false)
                                .amount(requestDTO.getPrice())
                                .discount(requestDTO.getDiscount())
                                .build()
                )
                .manufacturer(
                        Manufacturer.builder()
                                .uuid(manufacturerUuid)
                                .createdAt(now)
                                .updatedAt(now)
                                .isDeleted(false)
                                .name(requestDTO.getManufacturer())
                                .build()
                )
                .productCategory(productCategory.get())
                .tags(productTags)
                .rating(requestDTO.rating)
                .build();

        Product savedProduct = diagonAlleyRDBProductRepository.save(newProduct);

        Outbox outbox = Outbox.builder()
                .product(savedProduct)
                .uuid(productUuid)
                .createdAt(now)
                .updatedAt(now)
                .build();
        Outbox savedOutbox = diagonAlleyRDBOutboxRepository.save(outbox);
//        diagonAlleyOutboxEventPublisher.publishOutboxEvent(savedOutbox);
        return DiagonAlleyCreateProductResponseDTO.builder()
                .createdAt(now)
                .response("Product: " + productName + " has been created")
                .uuid(savedOutbox.getUuid().toString())
                .build();
    }

    @Override
    public DiagonAlleyCreateCategoryResponseDTO addCategory(DiagonAlleyCreateCategoryRequestDTO requestDTO) {
        String category = requestDTO.getCategory();
        UUID categoryUuid = timeBasedReorderedGenerator.generate();
        LocalDateTime now = now();
        Optional<ProductCategory> productCategory = diagonAlleyRDBCategoryRepository.findByName(category);
        if(productCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Could not add category, category already exists");
        }
        ProductCategory newCategory = ProductCategory.builder()
                .name(category)
                .uuid(categoryUuid)
                .createdAt(now)
                .updatedAt(now)
                .isDeleted(false)
                .build();
        diagonAlleyRDBCategoryRepository.save(newCategory);
        return DiagonAlleyCreateCategoryResponseDTO.builder()
                .response(category)
                .createdAt(now)
                .build();
    }

    @Override
    public DiagonAlleyUpdateProductResponseDTO updateProduct(DiagonAlleyUpdateProductRequestDTO requestDTO) {
        Optional<Product> productOptional;
        if(requestDTO.getUuid() != null){
            productOptional = diagonAlleyRDBProductRepository.findByUuid(UUID.fromString(requestDTO.getUuid()));
        }else if(requestDTO.getName() != null && !requestDTO.getName().isBlank()){
            productOptional = diagonAlleyRDBProductRepository.findByName(requestDTO.getName());
        }
        else{
            throw new RuntimeException("Specify either UUID or product name");
        }
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Could not update product, product does not exist");
        }
        LocalDateTime now = now();
        Product product = productOptional.get();
        String description = requestDTO.getDescription();
        String imageUrl = requestDTO.getImageUrl();
        String manufacturer = requestDTO.getManufacturer();
        String name = requestDTO.getName();
        double price = requestDTO.getPrice();
        double discount = requestDTO.getDiscount();
        double rating = requestDTO.getRating();
        List<String> tags = requestDTO.getTags();
        if(name != null){
            product.setName(name);
        }
        if(description != null){
            product.setDescription(description);
        }
        if(imageUrl != null){
            product.setImageURL(imageUrl);
        }
        if(manufacturer != null){
            diagonAlleyRDBManufacturerRepository.delete(product.getManufacturer());
            Manufacturer manufacturer1 = Manufacturer.builder()
                    .name(manufacturer)
                    .createdAt(now)
                    .updatedAt(now)
                    .build();
            product.setManufacturer(manufacturer1);
        }
        if(price != 0){
            diagonAlleyRDBPriceRepository.delete(product.getPrice());
            Price price1 = Price.builder()
                    .amount(price)
                    .discount(discount)
                    .createdAt(now)
                    .updatedAt(now)
                    .build();
            product.setPrice(price1);
        }
        if(rating != 0){
            product.setRating(rating);
        }
        if(tags != null){
            product.setTags(tags);
        }
        product.setUpdatedAt(now);
        Product updatedProduct = diagonAlleyRDBProductRepository.save(product);
//        diagonAlleyOutboxEventPublisher.publishOutboxUpdateEvent(product.getUuid())s
        return DiagonAlleyUpdateProductResponseDTO.builder()
                .response("Product: " + updatedProduct.getName() + " has been updated")
                .uuid(updatedProduct.getUuid().toString())
                .createdAt(now)
                .build();
    }

    /**
     * {@link DiagonAlleyDeleteProductRequestDTO}
     * @param requestDTO which has the product Uuid that needs to be deleted
     */

    public DiagonAlleyDeleteProductResponseDTO deleteProduct(DiagonAlleyDeleteProductRequestDTO requestDTO){
        UUID uuid = requestDTO.getUuid();
        Optional<Product> previousProductOptional = diagonAlleyRDBProductRepository.findByUuid(uuid);
        if(previousProductOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        Product previousProduct = previousProductOptional.get();
        if(previousProduct.isDeleted()){
            throw new ProductAlreadyDeletedException("Product already deleted!");
        }

        previousProduct.setDeleted(true);
        diagonAlleyRDBProductRepository.save(previousProduct);

        return DiagonAlleyDeleteProductResponseDTO.builder()
                .createdAt(LocalDateTime.now())
                .statusCode(200)
                .message("Product deleted successfully in database")
                .build();
    }

    @Override
    public DiagonAlleyGetProductsResponseDTO getProductByKeywords(String query) {
         List<org.theleakycauldron.diagonalley.daos.documents.Product> products = diagonAlleyElasticProductRepository.findProductByTagsEquals(Arrays.stream(query.split(" ")).toList());
         if(products.isEmpty()){
             throw new ProductNotFoundException("Could not find product with the given tags");
         }
         DiagonAlleyUtils.convertProductToGetProductsResponseDTOs(products);
            return DiagonAlleyGetProductsResponseDTO.builder()
                    .diagonAlleyGetProductResponseDTOList(DiagonAlleyUtils.convertProductToGetProductsResponseDTOs(products).getDiagonAlleyGetProductResponseDTOList())
                    .build();

    }

}
