# Ứng dụng To Do List

## A. Các định nghĩa khái niệm liên quan

### I. Java Collections Framework

Một framework cung cấp kiến trúc và thao tác tới nhóm các đối tượng bao gồm các thao tác như xem, thêm, xóa, tìm kiếm,
sắp xếp, phân loại.
Collections có ưu điểm hơn Array không cần phải gán dung lượng mà dung lượng có thể thay đối tùy vào số lượng đối tượng
lưu trữ nhưng bù lại Collection không thể lưu các biến nguyên thủy mà chỉ có thể lưu các Wrapper class của biến nguyên
thủy. Bù lại thì Java linh hoạt trong việc chuyển đổi (boxing và unboxing) từ primitive type sang wrapper class và ngược
lại nên không gây ảnh hưởng nhiều.  
Framework nằm trọn trong package java.utils gồm 2 bộ chính là Map và Collection. Đây chính là cấu trúc dữ liệu chính và
phổ biến nhất của Java
![](img/Java Collection.png)
Những interface chính của Collections Framework:

* Collection: định nghĩa phần cơ bản nhất của mọi collection bao gồm các thao tác như thêm (add()), xóa (remove()) hay
  kiểm tra sự tồn tại (contains()). Collection là interface con của java.lang.Iterable nên mọi collection đều có thể sử
  dụng foreach đồng thời Collection sử dụng kiểu generic nên có thể lưu mọi class. Collection gồm 3 interface con:
    * Set: một Collection không thể chứa 2 giá trị trùng lặp
    * List: một collection có thứ tự, có thể chứa các giá trị trùng lặp, truy cập các phần tử dựa trên index của chúng
    * Queue: một collection chứa các phần tử xử lý (hàng đợi) với thứ tự FIFO(first in - first out). Queue có một
      interface con là Deque giúp phần tử có thể chèn và lấy ra từ cả hai đầu (thêm LIFO (last in - fỉrst out))
* Map: là một đối tượng ánh xạ mỗi cặp Key - Value, mỗi Key ánh xạ một Value và danh sách Key không trùng lặp

Ngoài ra Collections Framework còn một class chỉ bao gồm các hàm và biến static là Collections để làm công cụ hỗ trợ cho
người dùng trong việc sắp xếp, tìm kiếm, vv

Trong khuôn khổ đề tài, chúng ta sẽ nói về ArrayList, Vector và Stack

1. ArrayList
    - là class implement từ interface List
    - ArrayList có chứa một biến thành viên là một mảng Object đồng thời ArrayList được thiết kế để tự mở rộng mảng khi
      mảng đó đầy nên kích thước của ArrayList không cố định
    - ArrayList duy trì thứ tự được thêm vào
    - Truy cập theo index nên có thể truy cập ngẫu nhiên nhưng bù lại, khi chèn, xóa sẽ dịch mangr hoặc đánh lại index
      nên thao tác chậm
2. Vector và Stack
    - Cũng như ArrayList, Vector cũng implement từ interface List, các hàm của Vector cũng không khác ArrayList
    - Các hàm của Vector đều yêu cầu synchronized (đồng bộ) tức chỉ có 1 thread được hoạt động trên 1 Vector
    - Vector có 1 class con là Stack hoạt đông theo cơ chế FIFO(first in - first out). Nhờ cơ chế synchronized khi nhiều
      Thread cùng truy cập vào Stack sẽ không bị lấy nhầm cùng 1 cái
    - Test: [SyncTest](src/main/java/blaplafla/todolist/models/test/SyncTest.java)
      - Tạo 2 đối tượng ArrayList và Stack ứng với mỗi đối tượng tạo 2 luồng add 5000 phần tử
      - Start 4 luồng trên và join lại
      - ArrayList sẽ không luôn cho ra kết quả là 10000 như Stack thậm chí còn phát sinh lỗi ArrayIndexOutOfBoundsException

### II. MergeSort

- Giải thuật sắp xếp trộn (Merge Sort) là thuật toán sắp xếp hiệu quả sử dụng thuật toán Divide and Conquer (chia để
  trị)
- Thuật toán hoạt động khá đơn giản:
    - Ở bước chia, chia đôi nhiều lần mảng ban đầu thành các mảnh con cho đến khi mỗi mảnh có kích thước là 1 hoặc 2.
      Khi đó sắp xếp mảng trong mảng con đơn giản chỉ là so sánh hai phần tử.
    - Ở bước trị, các mảng sẽ gộp lại với nhau, do các mảng đã sắp xếp ở trên nên chỉ cần so sánh giá trị đầu tiên của
      và thêm vào mảng kết quả
- Thuật toán được sử dụng nhiều do:
    - Thuật toán đơn giản, ổn định
    - Độ phức tạp thời gian thấp O(nlogn) trong mọi trường hợp
    - Độ phức tạp không gian là O(n)
    - Nhờ việc chia nhỏ các mảng để xử lý độc lập, thuật toán dễ dàng hoạt động trên đa luồng để xử lý nhanh hơn, hiệu
      quả hơn, xử dụng tối đa tài nguyên phần cứng. Thay vì chia đôi có thể chia thành nhiều mảnh hơn cho nhiều luồng xử
      lý một lúc nên đặc biệt thích hợp cho dữ liệu lớn.
- Trong khoa học máy tính, một thuật toán sắp xếp ổn định bảo toàn thứ tự các bản ghi với các khóa bằng nhau.
- Nhược điểm:
    - So sánh chậm hơn với các thuật toán khác cho các khối lượng nhỏ
    - Đòi hỏi một bộ nhớ tạm O(n) để chia nhỏ và xử lý khi làm việc với mảng (sắp xếp ngoài mảng ban đầu) vậy nên thuật toán chủ yếu sử dụng với
      LinkedList
    - Thuật toán mỗi lần sắp xếp sẽ chạy trên toàn bộ mảng nên bất kể mảng đã được sắp xếp hay chưa vẫn tốn một lượng
      thời gian

### III. Mô hình MVC
- MVC hay Model-View-Controller là một mô hình thiết kế phần mềm phổ biến được sử dụng để phát triển chương trình giao diện người dùng.
- Như tên gọi, MVC gồm 3 thành phần chính độc lập với nhau
  - Model: Quản lý dữ lệu, logic và các quy tắc, Model thường thao tác với cơ sở dữ liệu hoặc các list dữ liệu
  - View: là giao diện cho người dùng xem và gửi các yêu cầu tới Controller
  - Controller: đóng vai trò quản lý, tiếp nhận yêu cầu của gửi dùng để thao tác trên Model.
  - Ngoài ra mô hình có thể mở rộng ra thêm nhiều các thành phần khác để phục vụ việc xử lý yêu cầu của người dùng.
- Về cơ bản chương trình được xử lý như sau:
  - Bước 1: Người dùng gửi yêu cầu (request) cho Controller, Controller tiếp nhận
  - Bước 2: Controller kiểm tra request đầu vào, nếu liên quan đến dữ liệu sẽ gửi yêu cầu xử lý đến Model
  - Bước 3: Khi Model nhận yêu cầu từ Controller, Model sẽ truy xuất dữ liệu tính toán và xử lý để trả về Controller
  - Bước 4: Sau khi nhận dữ liệu của Model, Controller sẽ tiếp tục xử lý và gọi đến View cùng dữ liệu
  - Bước 5: View hiện thị ra dữ liệu cho người sử dụng và trở thành giao diện cho người dùng gửi yêu cầu lên Controller

  ![](img/mvc.png)
- Ưu điểm của mô hình:
  - Các thành phần tách biệt hoàn toàn với nhau chỉ gọi qua nhau thông qua các yêu cầu nên dễ dàng quản lý, nâng cấp, sửa chữa. Một phần bị hỏng thì chỉ có thể hỏng trong phạm vi Controller đó quản lý, sửa một phần thì tất cả vẫn có thể hoạt động bình thường theo thay đổi mới
  - Nhiều View: Do các thành phần tách biệt nên phát triển các View khác nhau hoàn toàn dễ dàng, một chương trình có thể chạy trên nhiều giao diện khác nhau như thông qua giao diện dòng lệnh hay giao diện đồ họa.
- Nhược điểm là 
  - Phức tạp khi xây dựng ban đầu hoặc hiểu code sẵn có khi tham gia vào dự án
  - Nhiều quy tắc phải theo (do tính khuôn mẫu của nó)
## B. Giới thiệu bài toán

### I. To-do List và Task Priority
###### _Danh sách công việc cần làm và mức độ ưu tiên công việc_

1. Lý do, tính cấp thiết
- Cuộc sống ngày càng bận rộn, số lượng công việc ngày càng nhiều, khi đến một mức nhất định, chúng ta không thể quản lý được hiệu quả.
- Những ứng dụng To-do List phổ biến hiện nay như Google Tasks, Microsoft To Do không có lựa chọn đặt mức độ ưu tiên của công việc rồi tự động sắp xếp thứ tự ưu tiên, buộc người dùng phải tự sắp xếp bằng tay nên không hiệu quả, các nhiệm vụ dễ bị chìm xuống dưới rơi vào quên lãng

⇨ Đòi hỏi một ứng dụng có thể tự động sắp xếp thứ tự công việc theo ưu tiên của người dùng
2. Mô tả bài toán
- Người dùng nhập các thông tin của công việc cũng như mức độ ưu tiên là một số dương.
- Điểm của công việc được tính bằng công thức (thời gian hạn chót - thời gian hiện tại) / mức độ ưu tiên
- Mức độ ưu tiên càng cao, càng sát deadline thì tử càng giảm, mẫu càng cao, phân số càng nhỏ điểm của công việc càng thấp, như vậy khi sắp xếp thứ tự công việc theo điểm tăng dần, công việc càng gấp sẽ nổi lên trên, công việc còn nhiều thời gian, độ ưu tiên thấp sẽ ở dưới.
- Do yêu cầu bài toán, để chính xác thuật toán được chọn phải xử lý nhanh với tốc độ thời gian thực (dưới 1s)

### II. Áp dụng cấu trúc dữ liệu và giải thuật vào bài toán

1. Cấu trúc dữ liệu (datastructures)
- Sử dụng List để lưu dữ liệu
- Trong bài xây dựng lại 2 class ArrayList thành SimpleArrayList và Stack thành SimpleStack
  - SimpleArrayList implement từ SimpleDataStructure với kiểu Generic các phần tử có class cha là Comparable tiện cho việc sắp xếp mảng, sử dụng để lưu trữ công việc chưa hoàn thành
  - SimpleStack implement từ SimpleDataStructure với kiểu Generic với index và Iterator có thứ tự ngược lại thứ tự nhập vào dùng để lưu công việc đã hoàn thành
2. Thuật toán sắp xếp
- Sử dụng thuật toán Merge Sort
- Thuật toán Merge Sort được cài đặt dưới dạng một object và được lưu làm biến thành viên của SimpleArrayList để thuận tiện cho việc sắp xếp
- Class MergeSort được thiết kế chạy đa luồng trên hai mảng dữ liệu riêng biệt tách ra từ mảng đầu vào để tận dụng phần cứng tăng tốc xử lý 
3. Mô hình
- Chương trình sử dụng mô hình MVC mở rộng
  - Controller:
    - Bao gồm MainController cùng các các Controller phụ
    - MainController là 1 singleton bao gồm các Controller phụ và các View nhằm quản lý nhất quán
    - Controller phụ sẽ là hằng sau khi khai báo để đảm bảo không bị lỗi liên quan đến các biến thành viên trong Controller phụ bị thay đổi khi thay Controller phụ
    - Các View của chương trình cũng sẽ lưu tại đây để thuận tiện cho việc gọi các View
  - Model:
    - Lưu các dữ liệu của chương trình
      - Thuật toán sắp xếp (aglorithms)
      - Cấu trúc dữ liệu (datastructures)
      - Từ điển cho việc sử dụng nhiều ngôn ngữ (dictionary)
      - Đối tượng Task (task)
    - Request:
      - Xử lý đầu vào người dùng nhập của chương trình
      - RequestValidation là class cha có extend là 
        - TerminalInputValidation dùng để nhập liệu từ Terminal
    - View:
      - Hiển thị thông tin cho người sử dụng
      - Interface View với 2 method run() không đối và run() có đối để chạy với 2 điều kiện: có đối truyền vào và không có đối truyền vào
      - Các package theo từng gói giao diện của View
- Sơ đồ quan hệ:

## C. Mô tả chương trình ứng dụng

### I. Tính năng:
1. Các tính năng đã hoàn thiện
- Xem thêm sửa xóa task
- Sắp xếp Task tự động theo thứ tự ưu tiên và thời gian
- Đổi ngôn ngữ
2. Các tính năng chưa phát triển và có thể phát triển thêm
- Sắp xếp thêm các task con trực tiếp ở index
- Thông báo

### II. Giao diện

| Tên                                                                |    CLI    |   GUI    |
|:-------------------------------------------------------------------|:---------:|:--------:|
| Trang đổi ngôn ngữ<br>SetLanguage                                  |  &#9745;  | &#9744;  |
| Nhập file<br>OpenFile                                              |  &#9745;  | &#9744;  |
| Lưu file<br>SaveFile                                               |  &#9745;  | &#9744;  |
| Danh sách các việc cần làm + việc cuối cùng đã hoàn thành<br>Index |  &#9744;  | &#9744;  |
| Danh sách các việc chưa hoàn thành<br>Undone                       |  &#9744;  | &#9744;  |
| Danh sách các việc đã làm<br>Done                                  |  &#9744;  | &#9744;  |
| Tạo việc mới<br>Create                                             |  &#9744;  | &#9744;  |
| Sửa việc đã tạo<br>Edit                                            |  &#9744;  | &#9744;  |
| Tạo công việc con<br>CreateSubTask                                 |  &#9744;  | &#9744;  |
| Xem chi tiết việc (Sửa Xóa Tích hoàn thành)<br>Detail              |  &#9744;  | &#9744;  |
| Xem chi tiết việc con (Sửa Xóa Tích hoàn thành)<br>DetailSubTask   |  &#9744;  | &#9744;  |

&#9745; hoàn tất  
&#9744; đang làm

### III. Mô tả từng giao diện

## Tài liệu tham khảo
- Java Docs 17
- GeeksForGeeks
- Baeldung.com
