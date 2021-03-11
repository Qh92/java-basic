### JDK8操作日期类型

Java 8新增了`LocalDate`和`LocalTime`接口，为什么要搞一套全新的处理日期和时间的API？因为旧的`java.util.Date`实在是太难用了。

`java.util.Date`月份从`0`开始，一月是`0`，十二月是`11`，变态吧！`java.time.LocalDate`月份和星期都改成了`enum`，就不可能再用错了。

`java.util.Date`和`SimpleDateFormatter`都不是线程安全的，而`LocalDate`和`LocalTime`和最基本的`String`一样，是不变类型，不但线程安全，而且不能修改。

`java.util.Date`是一个“万能接口”，它包含日期、时间，还有毫秒数，如果你只想用`java.util.Date`存储日期，或者只存储时间，那么，只有你知道哪些部分的数据是有用的，哪些部分的数据是不能用的。在新的Java 8中，日期和时间被明确划分为`LocalDate`和`LocalTime`，`LocalDate`无法包含时间，`LocalTime`无法包含日期。当然，`LocalDateTime`才能同时包含日期和时间。

新接口更好用的原因是考虑到了日期时间的操作，经常发生往前推或往后推几天的情况。用`java.util.Date`配合`Calendar`要写好多代码，而且一般的开发人员还不一定能写对。

### LocalDate

看看新的`LocalDate`怎么用：

```
// 取当前日期：
LocalDate today = LocalDate.now(); // -> 2014-12-24
// 根据年月日取日期，12月就是12：
LocalDate crischristmas = LocalDate.of(2014, 12, 25); // -> 2014-12-25
// 根据字符串取：
LocalDate endOfFeb = LocalDate.parse("2014-02-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
LocalDate.parse("2014-02-29"); // 无效日期无法通过：DateTimeParseException: Invalid date
```

日期转换经常遇到，比如：

```
// 取本月第1天：
LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
// 取本月第2天：
LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
// 取本月最后一天，再也不用计算是28，29，30还是31：
LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
// 取下一天：
LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
// 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
```

### LocalTime

`LocalTime`只包含时间，以前用`java.util.Date`怎么才能只表示时间呢？答案是，假装忽略日期。

`LocalTime`包含毫秒：

```
LocalTime now = LocalTime.now(); // 11:09:09.240
```

你可能想清除毫秒数：

```
LocalTime now = LocalTime.now().withNano(0)); // 11:09:09
```

构造时间也很简单：

```
LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00
```

时间也是按照ISO格式识别，但可以识别以下3种格式：

- 12:00
- 12:01:02
- 12:01:02.345

### JDBC

最新JDBC映射将把数据库的日期类型和Java 8的新类型关联起来：

```
SQL -> Java
--------------------------
date -> LocalDate
time -> LocalTime
timestamp -> LocalDateTime
```

再也不会出现映射到`java.util.Date`其中日期或时间某些部分为`0`的情况了。

最后总结一下，怎么才能愉快地处理日期和时间？答案是：立刻升级到Java 8！