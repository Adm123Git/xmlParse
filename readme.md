**Исходные данные**

1. Имеется файл <i>source_file.xml</i>. Файл содержит описание 3d-объектов. У каждого объекта 
есть координаты XYZ.<br>
В исходном файле 3d-объекты задаются тегом &lt;Neutral&gt; на произвольном уровне вложенности.
Среди всех них необходимо найти те теги &lt;Neutral&gt;,  внутри которых присутствует структура с координатами
&lt;Origin&gt;:<br>
&lt;Neutral&gt;<br>
&nbsp;&nbsp;&lt;Actor.Location&gt;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&lt;Pivot&gt;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Origin X="829360.5" Y="1832355" Z="147714" /&gt;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/Pivot&gt;<br>
&nbsp;&nbsp;&lt;/Actor.Location&gt;<br>
&nbsp;&nbsp;&lt;Actor.Color R="255" G="0" B="0" /&gt;<br>
&lt;/Neutral&gt;<br>

2. Задан цвет RGB (красный R=255, G=0, B=0).<br>
Цвет 3d объекта задается тегом &lt;Actor.Color&gt; внутри тега &lt;Neutral&gt;:<br>
&nbsp;&nbsp;&lt;Actor.Color R="255" G="0" B="0" /&gt;<br>
Этот тэг может быть задан или отсутствовать.

**Задание**

1. Заменить или добавить заданный цвет для каждого 3d объекта и сохранить результат в файл <i>target_file.xml</i>.
2. Результаты замены цвета, ошибки при работе должны быть записаны в лог-файл.

**Комментарии**

1. Поиск объектов <Neutral> лучше выполнять через xpath.
2. Для логирования можно использовать библиотеку log4j.