// Simple script to fix the placeOrder function
const fs = require('fs');

// Read the file
let content = fs.readFileSync('product-detail.html', 'utf8');

// Find the problematic simulation code and replace it
const oldCode = `            }, 2000);
        }`;
const newCode = `        }`;

// Replace the problematic code
if (content.includes(oldCode)) {
    content = content.replace(oldCode, newCode);
    
    // Write back to file
    fs.writeFileSync('product-detail.html', content, 'utf8');
    console.log('Successfully fixed placeOrder function');
} else {
    console.log('Could not find the problematic code');
}
