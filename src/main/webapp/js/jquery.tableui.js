/*
 * tableUI 0.1
 * Date: 2014-10-16
 * 使用tableUI可以方便地将表格提示使用体验。先提供的功能有奇偶行颜色交替，鼠标移上高亮显示
 */
(function($){
	$.fn.tableUI = function(options){
		var defaults = {
			evenRowClass:"evenRow",
			oddRowClass:"oddRow",
			activeRowClass:"activeRow",
			clickRowClass:"clickRow"
		}
		var options = $.extend(defaults, options);
		this.each(function(){
			var thisTable = $(this);
			$(thisTable).find("tr:even").addClass(options.evenRowClass);
			$(thisTable).find("tr:odd").addClass(options.oddRowClass);
			$(thisTable).find("tr").bind("mouseover",function(){
				$(this).removeClass(options.clickRowClass).addClass(options.activeRowClass);
			});
			$(thisTable).find("tr").bind("mouseout",function(){
				$(this).removeClass(options.clickRowClass).removeClass(options.activeRowClass);
			});
			$(thisTable).find("tr").bind("click",function(){
				$(this).addClass(options.clickRowClass);
			});
		});
	};
})(jQuery);