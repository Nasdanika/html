$(document).ready( function() {
    $('.nsd-app-header').addClass(['bg-primary', 'text-secondary']);
    $('.nsd-app-header .nav-link').addClass('text-secondary');
    $('.nsd-app-header-title').addClass(['display-4', 'text-secondary']);
    $('.nsd-app-header-navs').addClass('float-right');

    $('.nsd-app-navbar').addClass(['bg-secondary', 'p-0']);
    $('.nsd-app-navbar .navbar').addClass('mb-0');

    $('.nsd-app-navigation-panel').removeClass('col'); 
    $('.nsd-app-navigation-panel').addClass(['col-auto', 'border-right', 'border-secondary', 'pb-1']); 
    $('.nsd-app-footer').addClass(['bg-secondary', 'text-primary', 'text-center']);
    $('.nsd-app-footer .nsd-app-footer-navs').addClass('justify-content-center');            
});
