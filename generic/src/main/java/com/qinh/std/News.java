package com.qinh.std;

/**
 * 封装一个新闻类News，包含新闻标题，新闻作者，新闻内容，新闻类型三个属性，提供必要的访问器和修改器方法，重写toString方法，
 * 要求打印对象时输出格式为“标题；类型；作者”，要求只要新闻标题相同就判断为同一条新闻。
 * 在测试类中创建一个只能容纳该类对象的ArrayList集合，添加三条新闻。
 * 遍历集合，打印新闻标题，将新闻标题截取字符串到10个汉字的长度。
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-15-20:48
 */
public class News implements Comparable{

    private String title;

    private String auther;

    private String content;

    public News(String title, String auther, String content) {
        this.title = title;
        this.auther = auther;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "标题：'" + title + '\'' +
                ", 类型：'" + auther + '\'' +
                ", 作者：'" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof News){
            News n = (News) o;
            if (!this.title.equals(n.title)){
                return 1;
            }
        }
        return 0;
    }
}
