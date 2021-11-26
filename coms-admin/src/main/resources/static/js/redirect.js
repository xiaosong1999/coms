;
var isAnimating = false;
var globalValue = 300;

function changePage(url, bool) {
    isAnimating = true;
    // trigger page animation
    $('#main_div').addClass("fadeout");
    setTimeout(() => {
        loadNewContent(url, bool);
        $('#main_div').addClass("fadein");
        // $('main').removeClass("fadein");
    }, 500);

}

//获取urlparam
function getUrlParam(k) {
    var regExp = new RegExp('([?]|&)' + k + '=([^&]*)(&|$)');
    var result = window.location.href.match(regExp);
    if (result) {
        return decodeURIComponent(result[2]);
    } else {
        return null;
    }
}

function loadNewContent(url, bool) {
    var newSectionName = 'cd-' + url.replace('.html', '');
    // console.log(url);
    section = $('<div class="cd-main-content ' + newSectionName + '"></div>');

    section.load(url + ' .cd-main-content > *', function (event) {
        // load new content and replace <main> content with the new one
        console.log(section);
        $('#main_div').html(section);

        if (url != window.location) {
            //add the new page to the window.history

            setTimeout(() => {
                $('#main_div').removeClass("fadein");
                $('#main_div').removeClass("fadeout");
            }, 200);
            window.history.pushState({path: url}, '', url);
            isAnimating = false;
        }
    });
}

$(function () {

    $('#main_div').on('click', '[data-type="page-transition"]', function (event) {
        event.preventDefault();
        //detect which page has been selected
        var routerUrl = $(this).attr('href');
        var newPage = routerUrl.substring(routerUrl.lastIndexOf("/") + 1, routerUrl.indexOf("?"));
        for (var i = 0; i < newPage.length; i++) {
            if (newPage[i] >= 'A' && newPage[i] <= 'Z') {
                newPage = newPage.replace(newPage[i], '_' + newPage[i].toLowerCase());
            }
        }
        console.log(newPage);
        //if the page is not animating - trigger animation
        if (!isAnimating) changePage(newPage, true);
    });
    $(window).on('popstate', function () {
        var newPageArray = location.pathname.split('/'),
            //this is the url of the page to be loaded
            newPage = newPageArray[newPageArray.length - 1];
        if (!isAnimating) changePage(newPage);
    });
})