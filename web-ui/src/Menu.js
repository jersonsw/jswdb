const Menu = [
    {
        heading: 'Main Navigation',
        translate: 'sidebar.heading.HEADER'
    },
    {
        name: 'Movies',
        path: 'movies',
        icon : 'fa fa-film',
        translate: 'sidebar.nav.MOVIES'
    },
    {
        name: 'Scheduler',
        path: 'scheduler',
        icon : 'icon-grid',
        translate: 'sidebar.nav.SCHEDULER'
    },
    {
        name: 'Menu',
        icon: 'icon-speedometer',
        translate: 'sidebar.nav.MENU',
        label: { value: 1, color: 'info' },
        submenu: [{
            name: 'Submenu',
            translate: 'sidebar.nav.SUBMENU',
            path: 'submenu'
        }]
    }
];

export default Menu;
