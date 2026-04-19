package com.example.ProductManagement.Controller;

import com.example.ProductManagement.Entity.Product;
import com.example.ProductManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/add")
    public Product addAdmin(@RequestParam("name") String name,
                            @RequestParam("price") Double price,
                            @RequestParam("category") String category,
                            @RequestParam("stock") Integer stock,
                            @RequestParam("adminId") Long adminId,
                            @RequestParam(value = "images", required = false) MultipartFile[] images) {
        // Create product
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setStock(stock);
        
        // Handle images (up to 4)
        if (images != null && images.length > 0) {
            for (int i = 0; i < Math.min(images.length, 4); i++) {
                if (images[i] != null && !images[i].isEmpty()) {
                    try {
                        String fileName = saveImage(images[i]);
                        setImageField(product, i + 1, fileName);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
                    }
                }
            }
        }
        
        return productService.addByAdmin(product, adminId);
    }

    @PostMapping("/vendor/add")
    public Product addVendor(@RequestParam("name") String name,
                             @RequestParam("price") Double price,
                             @RequestParam("category") String category,
                             @RequestParam("stock") Integer stock,
                             @RequestParam("vendorId") Long vendorId,
                             @RequestParam(value = "images", required = false) MultipartFile[] images) {
        // Create product
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setStock(stock);
        
        // Handle images (up to 4)
        if (images != null && images.length > 0) {
            for (int i = 0; i < Math.min(images.length, 4); i++) {
                if (images[i] != null && !images[i].isEmpty()) {
                    try {
                        String fileName = saveImage(images[i]);
                        setImageField(product, i + 1, fileName);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
                    }
                }
            }
        }
        
        return productService.addByVendor(product, vendorId);
    }

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/vendor/{id}")
    public List<Product> getVendorProducts(@PathVariable Long id) {
        return productService.getVendorProducts(id);
    }
    
    // Simple image saving method
    private String saveImage(MultipartFile image) throws IOException {
        // Create upload directory if it doesn't exist
        File uploadDir = new File("uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // Generate unique filename
        String originalFilename = image.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String uniqueFilename = UUID.randomUUID().toString() + "." + fileExtension;
        
        // Save file
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
