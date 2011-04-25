(function($) {

    var $attributeEditor = $("#attributeEditor"),
        $content = $("#content"),
        $contentCounter = $("#contentCounter"),
        $newAttribute = $("#newAttribute"),
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

    function initAttributeEditor() {
        $newAttribute.click(function(e) {
            e.preventDefault();
            newAttribute();
        });
        $(".editor-row span.delete a", ".complex").live("click", function(e) {
            e.preventDefault();
            deleteAttribute($(this));
        });
    }

    function newAttribute() {
        var next = updateIndicies();
        $attributeEditor.append(ich.newAttributeTmpl({ idx: next }));
    }

    function deleteAttribute($elem) {
        var parent = $elem.parents(".editor-row");
        console.log("parent: %o", parent);
        $elem.die("click");
        $attributeEditor[0].removeChild(parent[0]);
        updateIndicies();
        console.log("Delete!");
    }

    function updateIndicies() {
        var current, idx, $input, $inputs, suffix;
        // Loop over each editor row and get the position. Then update the attribute.
        $(".editor-row", ".complex").each(function(i) {
            $inputs = $(this).find("input[type=text]");
            $($inputs).each(function(j, input) {
                $input = $(input);
                suffix = $input.attr("name").split(".")[1];
                current = "attributes[" + i + "]." + suffix;
                $input.attr("name", current).attr("id", current);
            });
            idx = i;
        });
        return idx + 1;
    }

    $(function() {
        limitMaxLength($content, $contentCounter);
        limitMaxLength($summary, $summaryCounter);
        initAttributeEditor();
    });

}(jQuery));