# Отчёт по задаче №28 "Мастер ключей" #

Двери меняют свои значения до тех пор, пока количество шагов step не станет равным k (step = k). Важно, что каждая дверь меняет своё значение только на шаге, кратном её индексу.  

Например: дверь с индексом 8 будет переключать своё значение на шагах 1, 2, 4, 8 (чётное число раз) (состояния 1, 0, 1, 0), дверь с индексом 9 - на шагах 1, 3, 9 (нечётное число раз), т.е. останется октрытой (состояния 1, 0, 1).
В процессе выполнения задания были протестированы 4 тестовых кейса на 5, 10, 15 и 20 значений.  

В процессе выявлена закономерность, что открытыми остаются те двери, индексы которых являются полными квадратами натуральных чисел 1, 2, 3, 4, 5 и т.д. (1, 4, 9, 16, 25 соответственно), именно у таких полных квадратов нечтное число делителей.

##
Ниже приведены результаты для этих 4-х тестовых кейсов.

k = 5  
res = 10010

k = 10  
res = 1001000010

k = 15  
res = 100100001000000

k = 20  
res = 10010000100000010000

##