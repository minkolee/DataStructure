package alog4e.chapter01.section02.exercise;

/**
 * 使用定长数组实现队列, 首先需要考虑的就是出列之后队列的改变应该如何. 有点像使用一个数组索引的循环. 但是这个时候要注意计数的问题.
 *
 * 考虑如下几点
 * 1 参数进来的时候, 创建指定长度的队列
 * 2 需要保存一个队列实际长度, 和当前队列头部的索引.
 * 3 当N等于指定长度的时候, 就要扩充数组, 然后复制数组的内容, 这里要注意, 复制数组就是固定的长度, 而不是从队列开始处.
 * 4 之后再添加
 */

public class EX010314 {

}
