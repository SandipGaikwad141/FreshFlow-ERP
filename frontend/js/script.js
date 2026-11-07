// ==========================================
// API & APP CONFIGURATION
// ==========================================
const API_BASE_URL = 'https://freshflow-erp.onrender.com/api';

// Helper function to sanitize image paths for Netlify hosting
// ==========================================
// API & APP CONFIGURATION
// ==========================================


// Helper function to sanitize image paths for both static assets and Render dynamic uploads
function cleanImagePath(url) {
    if (!url) return 'images/products/placeholder.jpg';

    // 1. If it's already a full HTTP/HTTPS URL, keep it intact
    if (url.startsWith('http://') || url.startsWith('https://')) {
        return url;
    }

    // 2. If it's a dynamic upload from admin or starts with 'uploads/', route to Render backend
    if (url.includes('Alphonso-Mango-') || url.startsWith('uploads/')) {
        const fileName = url.replace(/^uploads\//, '');
        return `https://freshflow-erp.onrender.com/uploads/${fileName}`;
    }

    // 3. Fallback for static default images hosted on Netlify
    let clean = url.replace(/^(\.\.\/)*frontend\//, '').replace(/^\/+/, '');
    if (!clean.startsWith('images/')) {
        clean = clean.startsWith('products/') ? `images/${clean}` : `images/products/${clean}`;
    }

    return clean;
}

// ==========================================
// PRODUCTS CATALOG DISPLAY LOGIC
// ==========================================
document.addEventListener('DOMContentLoaded', () => {
    const productCatalog = document.getElementById('productCatalog');
    const productCountElement = document.getElementById('productCount');

    if (!productCatalog) return; // Exit if not on products catalog page

    async function fetchProducts() {
        try {
            const response = await fetch(`${API_BASE_URL}/products`);
            if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
            
            const products = await response.json();
            renderProducts(products);
        } catch (error) {
            console.error('Error fetching products:', error);
            productCatalog.innerHTML = `<div class="col-12 text-center text-danger"><p>Failed to load products from server. Please check backend.</p></div>`;
        }
    }

    function renderProducts(products) {
        if (productCountElement) {
            productCountElement.textContent = `Displaying ${products.length} products`;
        }

        productCatalog.innerHTML = products.map(product => {
            const imagePath = cleanImagePath(product.imageUrl || product.image);
            return `
                <div class="col-md-4 col-sm-6 mb-4">
                    <div class="card h-100 product-card shadow-sm">
                        <img src="${imagePath}" 
                             class="card-img-top p-3" 
                             alt="${product.name}"
                             onerror="this.onerror=null; this.src='images/products/placeholder.jpg';">
                        <div class="card-body d-flex flex-column">
                            <span class="badge bg-light text-success align-self-start mb-2">${product.category || 'Fresh Produce'}</span>
                            <h5 class="card-title fw-bold">${product.name}</h5>
                            <p class="card-text text-muted flex-grow-1">${product.description || ''}</p>
                            <div class="d-flex gap-2 mt-3">
                                <button class="btn btn-outline-success w-50">Details</button>
                                <button class="btn btn-success w-50">Order</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }).join('');
    }

    fetchProducts();
});
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

        // Update product count header dynamically
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