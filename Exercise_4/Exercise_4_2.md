#
**Примечание к фразе "Не забывайте, что объекты обычным присваиванием не копируются" из задания 4.2:**
#
Объекты не копируются при обычном присваивании, потому что при этом создается ссылка на уже существующий объект. 
Таким образом, при присваивании объекта одной переменной другой переменной, обе переменные начинают ссылаться на один и тот же объект в памяти.
Правильным способом присвоения объектов является создание новых объектов с использованием ключевого слова "new" для каждого элемента массива.
В таком случае каждый элемент массива будет ссылаться на отдельный экземпляр объекта.
#
**Почему получился такой вывод?**
#
Получается такой вывод, потому что метод attack() переопределен в классах-наследниках Crossbow и Bow.
Каждый из этих методов выводит свое уникальное сообщение в консоль, позволяя различить, для какого класса он вызывается.
