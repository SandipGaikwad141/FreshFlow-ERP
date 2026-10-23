// ==========================================
// API & APP CONFIGURATION
// ==========================================
const API_BASE_URL = 'https://freshflow-erp.onrender.com/api';

// Helper function to sanitize image paths for Netlify hosting
function cleanImagePath(url) {
    if (!url) return 'images/products/placeholder.jpg';
    // Strips any leading ../frontend/ or frontend/ returned by database
    return url.replace(/^(\.\.\/)?frontend\//, '');
}

// Helper function to generate short teaser (5 words max)
function getShortDescription(desc) {
    if (!desc) return '';
    const words = desc.replace(/•/g, '').trim().split(/\s+/);
    return words.slice(0, 5).join(' ') + (words.length > 5 ? '...' : '');
}

// ==========================================
// DOM LOAD & INITIALIZATION
// ==========================================
document.addEventListener('DOMContentLoaded', () => {
    fetchProducts();
});

// ==========================================
// FETCH & RENDER PRODUCTS
// ==========================================
async function fetchProducts() {
    const catalogContainer = document.getElementById('productCatalog');
    const productCountLabel = document.getElementById('productCount');

    if (!catalogContainer) return;

    try {
        const response = await fetch(`${API_BASE_URL}/products`);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const products = await response.json();

        // Update product count header
        if (productCountLabel) {
            productCountLabel.textContent = `Displaying ${products.length} products`;
        }

        // Render Product Cards
        catalogContainer.innerHTML = products.map(product => {
            const cleanUrl = cleanImagePath(product.imageUrl);
            const shortDesc = getShortDescription(product.description);
            const safeDescription = (product.description || '').replace(/`/g, '\\`').replace(/'/g, "\\'");
            const safeName = (product.name || '').replace(/'/g, "\\'");

            return `
              <div class="product-card" data-category="${product.category}">
                <img src="${cleanUrl}" alt="${product.name}" onerror="this.src='images/products/placeholder.jpg'">
                <span class="category-tag">${product.category}</span>
                <h3>${product.name}</h3>
                
                <p class="description-teaser">${shortDesc}</p>
                
                <button class="btn-details" onclick="openProductModal('${safeName}', \`${safeDescription}\`, '${cleanUrl}')">
                  👁 View Details
                </button>
                
                <a href="https://wa.me/?text=I%20want%20to%20order%20${encodeURIComponent(product.name)}" target="_blank" class="btn-whatsapp">
                  Order on WhatsApp
                </a>
              </div>
            `;
        }).join('');

    } catch (error) {
        console.error('Error fetching products:', error);
        catalogContainer.innerHTML = `
            <div class="error-state">
                <p>Unable to connect to the server. Make sure backend is running.</p>
            </div>
        `;
    }
}

// ==========================================
// MODAL FUNCTIONALITY
// ==========================================
function openProductModal(name, description, imageUrl) {
    const modal = document.getElementById('productModal');
    const modalTitle = document.getElementById('modalTitle');
    const modalImage = document.getElementById('modalImage');
    const modalDescription = document.getElementById('modalDescription');

    if (modalTitle) modalTitle.textContent = name;
    if (modalImage) modalImage.src = imageUrl;
    if (modalDescription) modalDescription.textContent = description;

    if (modal) {
        modal.classList.add('show');
        modal.style.display = 'block';
    }
}

function closeProductModal() {
    const modal = document.getElementById('productModal');
    if (modal) {
        modal.classList.remove('show');
        modal.style.display = 'none';
    }
}

// Close modal when clicking outside content box
window.onclick = function(event) {
    const modal = document.getElementById('productModal');
    if (event.target === modal) {
        closeProductModal();
    }
};