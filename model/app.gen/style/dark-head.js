$(document).ready( function() {
    $('.nsd-app-header').addClass(['bg-dark', 'text-light']);
    $('.nsd-app-header .nav-link').addClass('text-light');
    $('.nsd-app-header-title').addClass(['display-4', 'text-light']);
    $('.nsd-app-header-navs').addClass('float-right');

    $('.nsd-app-navbar').addClass(['bg-light', 'p-0']);
    $('.nsd-app-navbar .navbar').addClass('mb-0');

    $('.nsd-app-navigation-panel').removeClass('col'); 
    $('.nsd-app-navigation-panel').addClass(['col-auto', 'border-right', 'border-secondary', 'pb-1']); 
    $('.nsd-app-footer').addClass(['bg-dark', 'text-light', 'text-center']);
    $('.nsd-app-footer .nsd-footer-action').addClass(['font-italic', 'text-light']);
    $('.nsd-app-footer .nsd-app-footer-navs').addClass('justify-content-center');        
});
