# Отчёт с комментариями к заданию 4.1 из части "Работа с файлами и каталогами"
```
  1. File root = new File(".");
  2  ArrayList<File> expand = new ArrayList<File>();
  3. expand.add(root);

  4. for(int depth = 0; depth < 10; depth++) {
  5.   File[] expandCopy = expand.toArray(new File[expand.size()]);
  6.   expand.clear();
  7.   for (File file : expandCopy) {
  8.     System.out.println(depth + " " + file);
  9.     if (file.isDirectory()) {
  10.      expand.addAll(Arrays.asList(file.listFiles()));
         }
       }
     }
```
##
1. В строке ```File root = new File(".")``` создаёт объект класса File, представляющий корневой каталог текущего рабочего каталога. Наэто указывает аргумент ```"."```.
```root``` - название экземпляра класса File.
##
2. В строке ```ArrayList<File> expand = new ArrayList<File>()``` создаётся ArrayList (динамически изменяемый массив), хранящий объекты типа File.
##
3. В строке ```expand.add(root)``` добавляется ```root``` (корневой каталог) в список ```expand```
##
4. В строке ```for(int depth = 0; depth < 10; depth++)``` начинается цикл, который будет выполняться 10 раз (0-9) (глубина вложенности).
##
5. В строке ```File[] expandCopy = expand.toArray(new File[expand.size()])``` создаём копию текущего массива ```expand```, с помощью метода ```toArray``` преобразуем список ArrayList в массив с размером ```expand.size()```
##
6. В строке ```expand.clear()``` очищаем исходный список ```expand``` с помощью метода ```clear()```
##
7. В строке ```for (File file : expandCopy)``` перебираем все файлы в копии (применяем цикл foreach)
##
8. В строке ```System.out.println(depth + " " + file)``` выводим в консоль текущую глубину и путь к файлу
##
9. В строке ```if (file.isDirectory())``` проверяем условие, является ли данный файл каталогом
##
10. В строке ```expand.addAll(Arrays.asList(file.listFiles()))``` добавляем в исходный список ```expand``` все элементы, удовлетворяющие условию.
Метод ```Arrays.asList()``` преобразует полученный массив файлов ```File[]```, возвращённый через ```file.listFiles()``` в ```List<File>```
