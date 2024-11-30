package org.theleakycauldron.diagonalley.services.implementations;

import com.fasterxml.uuid.impl.TimeBasedReorderedGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateCategoryRequestDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateCategoryResponseDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateProductRequestDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateProductResponseDTO;
import org.theleakycauldron.diagonalley.entities.Manufacturer;
import org.theleakycauldron.diagonalley.entities.Price;
import org.theleakycauldron.diagonalley.entities.Product;
import org.theleakycauldron.diagonalley.entities.ProductCategory;
import org.theleakycauldron.diagonalley.exceptions.CategoryAlreadyExistsException;
import org.theleakycauldron.diagonalley.exceptions.CategoryNotFoundException;
import org.theleakycauldron.diagonalley.repositories.DiagonAlleyRDBCategoryRepository;
import org.theleakycauldron.diagonalley.repositories.DiagonAlleyRDBProductRepository;
import org.theleakycauldron.diagonalley.services.DiagonAlleyService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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

    public DiagonAlleyServiceImpl(
            DiagonAlleyRDBProductRepository diagonAlleyRDBProductRepository,
            DiagonAlleyRDBCategoryRepository diagonAlleyRDBCategoryRepository,
            TimeBasedReorderedGenerator timeBasedReorderedGenerator
    ) {
        this.diagonAlleyRDBProductRepository = diagonAlleyRDBProductRepository;
        this.diagonAlleyRDBCategoryRepository = diagonAlleyRDBCategoryRepository;
        this.timeBasedReorderedGenerator = timeBasedReorderedGenerator;
    }


    @Transactional
    @Override
    public DiagonAlleyCreateProductResponseDTO addProduct(DiagonAlleyCreateProductRequestDTO requestDTO) {
        String productName = requestDTO.getName();
        String category = requestDTO.getProductCategory();
        UUID productUuid = timeBasedReorderedGenerator.generate();
        UUID priceUuid = timeBasedReorderedGenerator.generate();
        UUID manufacturerUuid = timeBasedReorderedGenerator.generate();
        LocalDateTime now = now();
        Optional<Product> product = diagonAlleyRDBProductRepository.findByName(productName);
        if(product.isPresent()){
            throw new RuntimeException("Could not add product, product already exists");
        }
        Optional<ProductCategory> productCategory = diagonAlleyRDBCategoryRepository.findByName(category);
        if(productCategory.isEmpty()){
            throw new CategoryNotFoundException("Could not add product, category does not exist");
        }

        Product newProduct = Product.builder()
                .name(productName)
                .uuid(productUuid)
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
                .tags(requestDTO.getTags())
                .rating(requestDTO.rating)
                .build();

        diagonAlleyRDBProductRepository.save(newProduct);
        return DiagonAlleyCreateProductResponseDTO.builder()
                .createdAt(now)
                .response(productName)
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
}
