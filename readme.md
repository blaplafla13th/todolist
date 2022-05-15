# Ứng dụng To Do List

## A. Các định nghĩa khái niệm liên quan
### I. Java Collections Framework
Một framework cung cấp kiến trúc và thao tác tới nhóm các đối tượng bao gồm các thao tác như xem, thêm, xóa, tìm kiếm, sắp xếp, phân loại.
Collections có ưu điểm hơn Array không cần phải gán dung lượng mà dung lượng có thể thay đối tùy vào số lượng đối tượng lưu trữ nhưng bù lại Collection không thể lưu các biến nguyên thủy mà chỉ có thể lưu các Wrapper class của biến nguyên thủy. Bù lại thì Java linh hoạt trong việc chuyển đổi (boxing và unboxing) từ primitive type sang wrapper class và ngược lại nên không gây ảnh hưởng nhiều.  
Framework nằm trọn trong package java.utils gồm 2 bộ chính là Map và Collection. Đây chính là cấu trúc dữ liệu chính và phổ biến nhất của Java
![](img/Java Collection.png)
Những interface chính của Collections Framework:  
* Collection: định nghĩa phần cơ bản nhất của mọi collection bao gồm các thao tác như thêm (add()), xóa (remove()) hay kiểm tra sự tồn tại (contains()). Collection là interface con của java.lang.Iterable nên mọi collection đều có thể sử dụng foreach đồng thời Collection sử dụng kiểu generic nên có thể lưu mọi class. Collection gồm 3 interface con:  
    * Set: một Collection không thể chứa 2 giá trị trùng lặp
    * List: một collection có thứ tự, có thể chứa các giá trị trùng lặp, truy cập các phần tử dựa trên index của chúng
    * Queue: một collection chứa các phần tử xử lý (hàng đợi) với thứ tự FIFO(first in - first out). Queue có một interface con là Deque giúp phần tử có thể chèn và lấy ra từ cả hai đầu (thêm LIFO (last in - fỉrst out))
* Map: là một đối tượng ánh xạ mỗi cặp Key - Value, mỗi Key ánh xạ một Value và danh sách Key không trùng lặp

Ngoài ra Collections Framework còn một class chỉ bao gồm các hàm và biến static là Collections để làm công cụ hỗ trợ cho người dùng trong việc sắp xếp, tìm kiếm, vv

Trong khuôn khổ đề tài, chúng ta sẽ nói về ArrayList, Vector và Stack
1. ArrayList
   - là class implement từ interface List
   - ArrayList có chứa một biến thành viên là một mảng Object đồng thời ArrayList được thiết kế để tự mở rộng mảng khi mảng đó đầy nên kích thước của ArrayList không cố định
   - ArrayList duy trì thứ tự được thêm vào 
   - Truy cập theo index nên có thể truy cập ngẫu nhiên nhưng bù lại, khi chèn, xóa sẽ dịch mangr hoặc đánh lại index nên thao tác chậm
2. Vector và Stack
    - Cũng như ArrayList, Vector cũng implement từ interface List, các hàm của Vector cũng không khác ArrayList
    - Các hàm của Vector đều yêu cầu synchronized (đồng bộ) tức chỉ có 1 thread được hoạt động trên 1 Vector
    - Vector có 1 class con là Stack hoạt đông theo cơ chế FIFO(first in - first out). Nhờ cơ chế synchronized khi nhiều Thread cùng truy cập vào Stack sẽ không bị lấy nhầm cùng 1 cái
    - Test: [SyncTest](src/main/java/blaplafla/todolist/models/test/SyncTest.java)
### II. MergeSort
- Giải thuật sắp xếp trộn (Merge Sort) là thuật toán sắp xếp hiệu quả sử dụng thuật toán Divide and Conquer (chia để trị)
- Thuật toán hoạt động khá đơn giản:
  - Ở bước chia, chia đôi nhiều lần mảng ban đầu thành các mảnh con cho đến khi mỗi mảnh có kích thước là 1 hoặc 2. Khi đó sắp xếp mảng trong mảng con đơn giản chỉ là so sánh hai phần tử.
  - Ở bước trị, các mảng sẽ gộp lại với nhau, do các mảng đã sắp xếp ở trên nên chỉ cần so sánh giá trị đầu tiên của và thêm vào mảng kết quả
- Thuật toán được sử dụng nhiều do:
  - Thuật toán đơn giản, ổn định
  - Độ phức tạp thời gian thấp O(nlogn) trong mọi trường hợp
  - Độ phức tạp không gian là O(n)
  - Nhờ việc chia nhỏ các mảng để xử lý độc lập, thuật toán dễ dàng hoạt động trên đa luồng để xử lý nhanh hơn, hiệu quả hơn, xử dụng tối đa tài nguyên phần cứng. Thay vì chia đôi có thể chia thành nhiều mảnh hơn cho nhiều luồng xử lý một lúc nên đặc biệt thích hợp cho dữ liệu lớn.
- Trong khoa học máy tính, một thuật toán sắp xếp ổn định bảo toàn thứ tự các bản ghi với các khóa bằng nhau.
- Nhược điểm:
  - So sánh chậm hơn với các thuật toán khác cho các khối lượng nhỏ
  - Đòi hỏi một bộ nhớ tạm O(n) để chia nhỏ và xử lý khi làm việc với mảng vậy nên thuật toán chủ yếu sử dụng với LinkedList
  - Thuật toán mỗi lần sắp xếp sẽ chạy trên toàn bộ mảng nên bất kể mảng đã được sắp xếp hay chưa vẫn tốn một lượng thời gian
### III. Mô hình MVC

## B. Giới thiệu bài toán
### I. To-do List và Task  
1. Lý do, tính cấp thiết
2. Ứng dụng  
### II. Áp dụng cấu trúc dữ liệu và giải thuật vào bài toán 
## C. Mô tả chương trình ứng dụng
Tính năng:

- xem thêm sửa xóa task

- sắp xếp cv theo thứ tự ưu tiên và thời gian

- đổi ngôn ngữ

Giao diện

| Tên                                                       | CLI     | Web | GUI |
|:----------------------------------------------------------|---------|-----|-----|
| Trang đổi ngôn ngữ                                        | &#9745; |&#9744;|&#9744;|
| Nhập file                                                 | &#9745; |&#9744;|&#9744;|
| Lưu file                                                  | &#9745; |&#9744;|&#9744;|
| Danh sách các việc cần làm + việc cuối cùng đã hoàn thành | &#9744; |&#9744;|&#9744;|
| Danh sách các việc đã làm                                 | &#9744; |&#9744;|&#9744;|
| Tạo việc mới                                              | &#9744; |&#9744;|&#9744;|
| Sửa việc đã tạo                                           | &#9744; |&#9744;|&#9744;|
| Tạo công việc con                                         | &#9744; |&#9744;|&#9744;|
| Xem chi tiết việc (Sửa Xóa Tích hoàn thành)               | &#9744; |&#9744;|&#9744;|

&#9745; hoàn tất  
&#9744; đang làm
## Tài liệu tham khảo

