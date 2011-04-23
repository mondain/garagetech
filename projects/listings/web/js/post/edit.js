(function($) {

    var $content = $("#content"),
        $contentCounter = $("#contentCounter"),
        $summary = $("#summary"),
        $summaryCounter = $("#summaryCounter");

    function limitMaxLength($formElem, $countElem) {
        $formElem.limitMaxLength({
            onEdit: function(remaining) {
                switch (remaining) {
                    case 1:
                        $countElem.html(remaining + " character remaining");
                        break;
                    default:
                        $countElem.html(remaining + " characters remaining");
                        break;
                }
                if (remaining > 0) {
                    $countElem.removeClass("error");
                }
            },
            onLimit: function() {
                $countElem.addClass("error");
            }
        });
    }

    $(function() {
        limitMaxLength($content, $contentCounter);
        limitMaxLength($summary, $summaryCounter);
    });

}(jQuery));