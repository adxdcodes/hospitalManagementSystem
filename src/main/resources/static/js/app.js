// Common utility functions for the application

// API base URL
const API_BASE_URL = '/api';

// Auth token management
function getToken() {
    return localStorage.getItem('token');
}

function setToken(token) {
    localStorage.setItem('token', token);
}

// Make API requests with auth token
async function apiRequest(endpoint, options = {}) {
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };

    const token = getToken();
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
        ...options,
        headers
    });

    return response;
}

// Date formatting utility
function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
}

// Time formatting utility
function formatTime(timeString) {
    const [hours, minutes] = timeString.split(':');
    const hour = parseInt(hours);
    const ampm = hour >= 12 ? 'PM' : 'AM';
    const displayHour = hour > 12 ? hour - 12 : hour;
    return `${displayHour}:${minutes} ${ampm}`;
}

// Show notification (Toast)
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    
    // Color schemes for different types
    const colors = {
        success: { bg: '#10b981', border: '#059669' },
        danger: { bg: '#ef4444', border: '#dc2626' },
        warning: { bg: '#f59e0b', border: '#d97706' },
        info: { bg: '#3b82f6', border: '#2563eb' }
    };
    
    const colorScheme = colors[type] || colors.info;
    
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background-color: ${colorScheme.bg};
        color: white;
        padding: 16px 24px;
        border-radius: 8px;
        box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
        z-index: 9999;
        font-weight: 500;
        font-size: 14px;
        line-height: 1.5;
        border-left: 4px solid ${colorScheme.border};
        animation: slideIn 0.3s ease-out;
        max-width: 400px;
        word-wrap: break-word;
    `;
    
    notification.textContent = message;
    document.body.appendChild(notification);
    
    // Add animation
    const style = document.createElement('style');
    if (!document.getElementById('toastStyles')) {
        style.id = 'toastStyles';
        style.textContent = `
            @keyframes slideIn {
                from {
                    transform: translateX(400px);
                    opacity: 0;
                }
                to {
                    transform: translateX(0);
                    opacity: 1;
                }
            }
            @keyframes slideOut {
                from {
                    transform: translateX(0);
                    opacity: 1;
                }
                to {
                    transform: translateX(400px);
                    opacity: 0;
                }
            }
        `;
        document.head.appendChild(style);
    }
    
    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease-out';
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 3000);
}

// Validate email
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Validate phone number
function isValidPhone(phone) {
    const phoneRegex = /^[\d\s\-\+\(\)]+$/;
    return phoneRegex.test(phone) && phone.replace(/\D/g, '').length >= 10;
}

// Local storage management
const storage = {
    setUser: (user) => localStorage.setItem('user', JSON.stringify(user)),
    getUser: () => JSON.parse(localStorage.getItem('user')),
    removeUser: () => localStorage.removeItem('user'),
    setToken: (token) => localStorage.setItem('token', token),
    getToken: () => localStorage.getItem('token'),
    removeToken: () => localStorage.removeItem('token'),
    clear: () => localStorage.clear()
};

// Session management
const session = {
    isAuthenticated: () => !!storage.getToken(),
    isPatient: () => storage.getUser()?.role === 'PATIENT',
    isDoctor: () => storage.getUser()?.role === 'DOCTOR',
    logout: () => {
        storage.clear();
        window.location.href = '/index.html';
    }
};

// Export for use in modules
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        apiRequest,
        formatDate,
        formatTime,
        showNotification,
        isValidEmail,
        isValidPhone,
        storage,
        session
    };
}
