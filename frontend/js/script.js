// ==========================================
// API & APP CONFIGURATION
// ==========================================
const API_BASE_URL = 'https://freshflow-erp.onrender.com/api';
const RENDER_ORIGIN = 'https://freshflow-erp.onrender.com';

// Helper function to sanitize image paths for both static assets and Render dynamic uploads
// Helper function to sanitize image paths
function cleanImagePath(url) {
    if (!url) return 'images/products/placeholder.jpg';

    // 1. Full HTTP/HTTPS URLs keep intact (e.g. Cloudinary)
    if (url.startsWith('http://') || url.startsWith('https://')) {
        return url;
    }

    // 2. Dynamic uploads in uploads/ folder -> Point to Render
    if (url.startsWith('uploads/') || url.includes('Alphonso-Mango-')) {
        // Remove leading slash if present
        const cleanUrl = url.replace(/^\//, ''); 
        return `${RENDER_ORIGIN}/${cleanUrl}`;
    }

    // 3. FIX: Static images -> Strip "frontend/" and load from Netlify
    // This regex replaces "frontend/" or "/frontend/" with an empty string
    let clean = url.replace(/^\/?frontend\//, ''); 
    
    // Remove any remaining leading slashes
    clean = clean.replace(/^\/+/, '');

    // 4. Ensure path starts with "images/"
    if (!clean.startsWith('images/')) {
        // If it looks like "products/exotic/...", add "images/"
        clean = `images/${clean}`;
    }

    // Safety check to prevent "images/images/..."
    if (clean.startsWith('images/images/')) {
        clean = clean.replace('images/images/', 'images/');
    }

    return clean;
}


// Helper function to generate short teaser (5 words max)
function getShortDescription(desc) {
    if (!desc) return '';
    const words = desc.replace(/•/g, '').trim().split(/\s+/);
    return words.slice(0, 5).join(' ') + (words.length > 5 ? '...' : '');
}

// ==========================================
// PRODUCTS CATALOG DISPLAY LOGIC
// ==========================================
document.addEventListener('DOMContentLoaded', () => {
    fetchProducts();
});

async function fetchProducts() {
    const catalogContainer = document.getElementById('productCatalog');
    const productCountLabel = document.getElementById('productCount');

    if (!catalogContainer) return;

    try {
        const response = await fetch(`${API_BASE_URL}/products`);
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

        const products = await response.json();

        // Update product count header dynamically
        if (productCountLabel) {
            productCountLabel.textContent = `Displaying ${products.length} products`;
        }

        // Render Product Cards
        catalogContainer.innerHTML = products.map(product => {
            const cleanUrl = cleanImagePath(product.imageUrl || product.image);
            const shortDesc = getShortDescription(product.description);
            const safeDescription = (product.description || '').replace(/`/g, '\\`').replace(/'/g, "\\'");
            const safeName = (product.name || '').replace(/'/g, "\\'");
            const category = product.category || 'Fresh Produce';

            return `
                <div class="col-md-4 col-sm-6 mb-4" data-category="${category}">
                    <div class="card h-100 product-card shadow-sm">
                        <img src="${cleanUrl}" 
                             class="card-img-top p-3" 
                             alt="${product.name}"
                             onerror="this.onerror=null; this.src='images/products/placeholder.jpg';">
                        <div class="card-body d-flex flex-column">
                            <span class="badge bg-light text-success align-self-start mb-2">${category}</span>
                            <h5 class="card-title fw-bold">${product.name}</h5>
                            <p class="card-text text-muted flex-grow-1">${shortDesc}</p>
                            <div class="d-flex gap-2 mt-3">
                                <button class="btn btn-outline-success w-50" onclick="openProductModal('${safeName}', \`${safeDescription}\`, '${cleanUrl}')">
                                    👁 Details
                                </button>
                                <a href="https://wa.me/?text=I%20want%20to%20order%20${encodeURIComponent(product.name)}" target="_blank" class="btn btn-success w-50 d-flex align-items-center justify-content-center">
                                    Order
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }).join('');

    } catch (error) {
        console.error('Error fetching products:', error);
        catalogContainer.innerHTML = `
            <div class="col-12 text-center text-danger py-5">
                <p class="fs-5">Unable to connect to the server. Please check backend status.</p>
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