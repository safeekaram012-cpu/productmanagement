const fs = require('fs');

// Read the file
let content = fs.readFileSync('product-detail.html', 'utf8');

// Find the placeOrder function and replace simulation with navigation
const startFunction = content.indexOf('function placeOrder()');
const endFunction = content.indexOf('}', content.indexOf('}', startFunction) + 1);

if (startFunction !== -1 && endFunction !== -1) {
    // Replace entire function content
    const newFunction = `function placeOrder() {
            if (!selectedProduct) {
                showError('No product selected for ordering.');
                return;
            }
            
            // Check if product is in stock
            if (selectedProduct.stock <= 0) {
                showError('This product is currently out of stock.');
                return;
            }
            
            // Store product in sessionStorage and navigate to order page
            sessionStorage.setItem('selectedProduct', JSON.stringify(selectedProduct));
            
            // Navigate to order page
            window.location.href = 'order.html';
        }`;
    
    // Replace the function
    content = content.substring(0, startFunction) + newFunction + content.substring(endFunction);
    
    // Write back to file
    fs.writeFileSync('product-detail.html', content, 'utf8');
    
    console.log('Fixed placeOrder function - now navigates to order.html');
} else {
    console.log('Could not find placeOrder function');
}
