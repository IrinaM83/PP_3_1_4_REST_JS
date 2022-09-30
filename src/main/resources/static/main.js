/**
 *
 */

$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (users) {
            $('.myForm #id').val(users.id);
            $('.myForm #username').val(users.username);
            $('.myForm #lastName').val(users.lastName);
            $('.myForm #age').val(users.age);
            $('.myForm #email').val(users.email);
        });
        $('.myForm #editModal').modal();
    });

    $('table .dltBtn').on('click',function (event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user) {
            $('.myFormDlt #idDlt').val(user.id);
            $('.myFormDlt #usernameDlt').val(user.username);
            $('.myFormDlt #lastNameDlt').val(user.lastName);
            $('.myFormDlt #ageDlt').val(user.age);
            $('.myFormDlt #emailDlt').val(user.email);
        });
        $('.myFormDlt #dltModal').modal();
    });
});