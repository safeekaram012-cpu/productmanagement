package com.example.ProductManagement.Service;

import com.example.ProductManagement.Entity.Product;
import com.example.ProductManagement.Entity.User;
import com.example.ProductManagement.Enum.Role;
import com.example.ProductManagement.Repository.ProductRepository;
import com.example.ProductManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Product addByAdmin(Product product, Long adminId) {
        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + adminId + ". Please create an admin user first."));

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN allowed");
        }

        return productRepository.save(product);
    }

    public Product addByVendor(Product product, Long vendorId) {
        User user = userRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + vendorId + ". Please create a vendor user first."));

        if (user.getRole() != Role.VENDOR) {
            throw new RuntimeException("Only VENDOR allowed");
        }

        product.setVendorId(vendorId);
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getVendorProducts(Long vendorId) {
        return productRepository.findByVendorId(vendorId);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    public Product updateProduct(Long id, String name, Double price, String category, Integer stock, MultipartFile[] images) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Update product details
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setStock(stock);

        // Handle images if provided
        if (images != null && images.length > 0) {
            for (int i = 0; i < Math.min(images.length, 4); i++) {
                if (images[i] != null && !images[i].isEmpty()) {
                    try {
                        String fileName = saveImage(images[i]);
                        setImageField(product, i + 1, fileName);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
                    }
                }
            }
        }

        return productRepository.save(product);
    }

    // Helper method to save image (copied from controller)
    private String saveImage(MultipartFile image) throws IOException {
        File uploadDir = new File("uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = image.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String uniqueFilename = java.util.UUID.randomUUID().toString() + "." + fileExtension;

        Path filePath = Paths.get("uploads/" + uniqueFilename);
        Files.write(filePath, image.getBytes());

        return uniqueFilename;
    }

    // Helper method to get file extension
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "jpg";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    // Helper method to set image field
    private void setImageField(Product product, int imageIndex, String fileName) {
        switch (imageIndex) {
            case 1:
                product.setImage1(fileName);
                break;
            case 2:
                product.setImage2(fileName);
                break;
            case 3:
                product.setImage3(fileName);
                break;
            case 4:
                product.setImage4(fileName);
                break;
        }
    }
}
