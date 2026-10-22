// Function to generate short teaser (5 words max)
function getShortDescription(desc) {
    if (!desc) return '';
    const words = desc.replace(/•/g, '').trim().split(/\s+/);
    return words.slice(0, 5).join(' ') + (words.length > 5 ? '...' : '');
}

// Function to clean image paths for Netlify
function cleanImagePath(url) {
    if (!url) return 'images/products/placeholder.jpg';
    // Remove '../frontend/' or 'frontend/' prefixes
    return url.replace(/^\.\.\/frontend\//, '').replace(/^frontend\//, '');
}

// Inside your card rendering loop/function:
const cleanUrl = cleanImagePath(product.imageUrl);

const cardHTML = `
  <div class="product-card">
    <img src="${cleanUrl}" alt="${product.name}">
    <span class="category-tag">${product.category}</span>
    <h3>${product.name}</h3>
    
    <!-- Shortened description on card -->
    <p class="description-teaser">${getShortDescription(product.description)}</p>
    
    <!-- View Details button -->
    <button class="btn-details" onclick="openProductModal('${product.name}', \`${product.description.replace(/`/g, '\\`')}\`, '${cleanUrl}')">
      👁 View Details
    </button>
    
    <a href="https://wa.me/?text=I%20want%20to%20order%20${encodeURIComponent(product.name)}" class="btn-whatsapp">
      Order on WhatsApp
    </a>
  </div>
`;