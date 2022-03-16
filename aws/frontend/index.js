function upload() {
    let requestBody = {};
    $('.form input').each((i, e) => {
        let $input = $(e)
        requestBody[$input.attr('name')] = $input.val()
    });

    $.ajax('http://localhost:8080/product', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(requestBody)
    }).done(() => {
        alert('Successfully uploaded')
    }).fail(() => {
        alert('Failed')
    });
}

function update() {
    let requestBody = {};
    $('.form input').each((i, e) => {
        let $input = $(e)
        requestBody[$input.attr('name')] = $input.val()
    });

    $.ajax('http://localhost:8080/product', {
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(requestBody)
    }).done(() => {
        alert('Successfully updated')
    }).fail(() => {
        alert('Failed')
    });
}