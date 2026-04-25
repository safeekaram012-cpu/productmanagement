// Simple script to fix placeOrder function
const fs = require('fs');

// Read the file
let content = fs.readFileSync('product-detail.html', 'utf8');

// Find the problematic simulation code that shows "Order Completed"
const problematicCode = `document.getElementById('successMessage').style.display = 'block';
                document.getElementById('errorMessage').style.display = 'none';
                
                // Reset button
                orderBtn.disabled = false;
                orderBtn.innerHTML = '<i class="fas fa-shopping-bag me-2"></i>Order Now';
                
                // Scroll to success message
                document.getElementById('successMessage').scrollIntoView({ behavior: 'smooth' });
                
                // Optionally, you could update stock count here
                // selectedProduct.stock -= 1;
                // displayProductDetails(selectedProduct);
                
                // Show success message
                showSuccess('Order placed successfully! You will receive a confirmation shortly.');
            }, 2000);`;

// Remove the problematic simulation code
const fixedContent = content.replace(problematicCode, '');

// Write back to file
fs.writeFileSync('product-detail.html', fixedContent, 'utf8');

console.log('Successfully removed simulation code - now navigates to order.html');
