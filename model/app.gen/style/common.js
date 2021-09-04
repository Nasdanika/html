// Common styling  
$(document).ready( function() {
    $('.nsd-app-header-navs').addClass('float-right');    
    $('.nsd-app-navbar .navbar').addClass('mb-0');

    $('.nsd-app-navigation-panel').removeClass('col'); 
    $('.nsd-app-navigation-panel').addClass(['col-auto', 'border-right', 'border-secondary', 'pb-1']);
    
    $('.nsd-app-content-panel-title').addClass('display-4');    
    $('.nsd-app-content-panel-navs').addClass('float-right');
    $('.nsd-app-content-panel-breadcrumb').addClass('mt-1');
    $('.nsd-app-content-panel-content-row').addClass('mt-2');
    
    $('.nsd-app-content-panel-left-navigation').removeClass('col'); 
    $('.nsd-app-content-panel-left-navigation').addClass(['col-auto', 'border-right', 'border-secondary', 'pb-1']);

    $('.nsd-app-content-panel-float-left-navigation').addClass(['float-left', 'mr-2']);
    
    $('.nsd-app-content-panel-right-navigation').removeClass('col'); 
    $('.nsd-app-content-panel-right-navigation').addClass(['col-auto', 'border-left', 'border-secondary', 'pb-1']);

    $('.nsd-app-content-panel-float-right-navigation').addClass(['float-right', 'ml-2']);
    
    $('.nsd-app-footer .nsd-app-footer-navs').addClass('justify-content-center');        
});
