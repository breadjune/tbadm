async function myRequests(url, data, method, callback) {
    $.ajax({
        url: url,
        data: data,
        type: method,
        success: callback
    });
}