function fadeIn(selector, duration = 1000, callback) {
    const el = document.querySelector(selector);
    el.style.opacity = 0;
    el.style.transition = `opacity ${duration}ms ease`;
    requestAnimationFrame(() => {
        el.style.opacity = 1;
    });
    if (callback) setTimeout(callback, duration);
}

function fadeInSlideUp(selector, duration = 800, delay = 0) {
    const el = document.querySelector(selector);
    el.style.opacity = 0;
    el.style.transform = 'translateY(30px)';
    el.style.transition = `opacity ${duration}ms ease ${delay}ms, transform ${duration}ms ease ${delay}ms`;
    requestAnimationFrame(() => {
        el.style.opacity = 1;
        el.style.transform = 'translateY(0)';
    });
}
