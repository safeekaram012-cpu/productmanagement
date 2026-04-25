// Simple script to fix placeOrder function
const fs = require('fs');

// Read the file
let content = fs.readFileSync('product-detail.html', 'utf8');

// Find and remove the problematic simulation code that shows "Order Completed"
const lines = content.split('\n');
let fixedLines = [];
let inSimulationSection = false;

for (let i = 0; i < lines.length; i++) {
    const line = lines[i];
    
    // Check if we're entering the problematic simulation section
    if (line.includes("document.getElementById('successMessage').style.display = 'block'")) {
        inSimulationSection = true;
    }
    
    // If we're in simulation section and find the end
    if (inSimulationSection && line.includes("showSuccess('Order placed successfully!")) {
        // Skip all lines in the simulation section
        continue;
    }
    
    // If we're not in simulation section, keep the line
    if (!inSimulationSection) {
        fixedLines.push(line);
    }
}

// Write the fixed content back
const fixedContent = fixedLines.join('\n');
fs.writeFileSync('product-detail.html', fixedContent, 'utf8');

console.log('Successfully removed simulation code from placeOrder function - now it only navigates to order.html');
