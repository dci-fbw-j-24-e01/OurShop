
function toggleNavbar() {
    const links = document.querySelector('.navbar-links');
    const toggleButton = document.querySelector('.navbar-toggle');

    links.classList.toggle('active');

    // Update the aria-expanded attribute in navbar.html based on the current state of the toggle bar
    const isExpanded = toggleButton.getAttribute("aria-expanded") === "true";
    toggleButton.setAttribute("aria-expanded", isExpanded ? "false" : "true");
}
