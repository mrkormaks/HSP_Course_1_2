# Отчёт по использованию линтера в IDEA
Для изучения работы линтера использовал три своих учебных проекта.
## Рекомендации линтера (приложение RandomPairs)
![image](https://github.com/user-attachments/assets/368791d0-7064-47b9-b8f3-a3b5f463b9e3)
### Рекомендация 1. Выполнять итерацию по мапе по "entrySet" вместо "keySet"
До правки
![image](https://github.com/user-attachments/assets/c43776e3-0c08-4aa9-8a1f-1d2cf82a9a65)
После правки
![image](https://github.com/user-attachments/assets/f89c0053-345f-4d9b-a31b-37630789b900)
### Рекомендация 2. Использовать map.computeIfAbsent() при добавлении значений в мапу
До правки
![image](https://github.com/user-attachments/assets/ce63d85a-2a39-454b-84ea-f977f5d88f5f)
После правки
![image](https://github.com/user-attachments/assets/97fd66dd-ffdb-4dac-ba54-2b7e4a213e5f)
## Рекомендации линтера (приложение ZipFileUpdater)
### Рекомендация 1. Использовать специализированное исключение вместо общего
До правки
![image](https://github.com/user-attachments/assets/6f0be92d-6cd7-48ee-938c-2e0c96231046)
После правки
![image](https://github.com/user-attachments/assets/2cfb52ea-fb3b-45da-89bb-a132deced54a)
### Рекомендация 2. Использовать Logger вместо System.out.println()
До правки
![image](https://github.com/user-attachments/assets/0db981d3-3eff-4e5e-8a0d-b76660e46beb)
После правки
![image](https://github.com/user-attachments/assets/3a2d441a-95fd-4433-a44d-0361102b5a42)
## Рекомендации линтера (приложение TwoLists)
В данном приложение проверил рекомендации линтера в тестах
![image](https://github.com/user-attachments/assets/18d6966f-2763-478a-af14-7d853b88ce7d)
### Рекомендация 1. Определение констант
До правки
![image](https://github.com/user-attachments/assets/37c4f3fa-8879-4843-9670-6372f0f64ea6)
После правки
![image](https://github.com/user-attachments/assets/a7eda9b8-d3d2-4410-bec4-3a35491ddd33)
### Рекомендация 2. Использовать try-with-resources или закрывать поток в блоке finally
До правки
![image](https://github.com/user-attachments/assets/93645374-6298-4e7a-9f52-e22bf8e33185)
После правки (try-with-resources)
![image](https://github.com/user-attachments/assets/46eea95b-6749-4e45-8c1f-92f36c8171d2)
### Также линтер дал рекомендацию убрать ассерты из продуктового кода, мотивируя тем, что "Assertions are intended to be used in test code, but not in production code.", но т.к. этот файл и содержит класс с тестами, то эту рекомендацию можно проигнорировать или удалить это правило.
##
В каждом учебном приложении линтер предлагает упаковать файл в отдельный package, рекомендация понятна, но из-за малого объема проектов делать этого не стал.

Все изменения по предложенным рекомедациям, предоставленные в отчёте, залиты в git-репозиторий.
