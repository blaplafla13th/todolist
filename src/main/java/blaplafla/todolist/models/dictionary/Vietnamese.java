package blaplafla.todolist.models.dictionary;

public class Vietnamese
        extends Dictionary {
    @Override
    public String errorExplain(int errorCode) {
        return switch (errorCode) {
            case 100 -> "Thành công";
            case 200 -> "Không tìm thấy file dữ liệu";
            case 201 -> "Không tìm thấy file ngôn ngữ";
            case 202 -> "Không tìm thấy file giao diện";
            case 300 -> "Không thể đọc ghi file dữ liệu";
            case 301 -> "Không thể đọc ghi file ngôn ngữ";
            case 302 -> "Không thể đọc ghi file giao diện";
            case 303 -> "Không thể tạo icon ở taskbar";
            case 304 -> "Không thể để UTF-8 cho terminal";
            case 400 -> "Dữ liệu của tệp không hợp lệ";
            case 401 -> "Dữ liệu của tệp ngôn ngữ không hợp lệ";
            case 402 -> "Đầu vào không hợp lệ";
            case 500 -> "Lỗi nhập liệu người dùng";
            case 501 -> "Không tìm thấy id được cung cấp";
            case 502 -> "Ngày không hợp lệ";
            case 503 -> "Thời gian không hợp lệ";
            default -> "Không xác định";
        };
    }

    @Override
    public String prettyTime(long day, long hour, long minute, long second) {
        return String.format("Còn lại %d ngày %d giờ %d phút %d giây", day, hour, minute, second);
    }

    @Override
    public String label(String label) {
        return switch (label) {
            case "todolist-name" -> "Danh sách việc cần làm của ";
            case "current-lang" -> "Ngôn ngữ hiện tại: ";
            case "set-lang" -> "Đổi ngôn ngữ sang: ";
            case "input-num-option" -> "Nhập số của lựa chọn: ";
            case "def" -> "Trả về giá trị mặc định: ";
            case "input-date" -> "Nhập giá trị ngày (định dạng năm-tháng-ngày:) ";
            case "input-time" -> "Nhập giá trị thời gian (định dạng giờ:phút:giây (24h):) ";
            case "list-undone" -> "Danh sách công việc chưa hoàn thành: ";
            case "list-done" -> "Danh sách công việc đã hoàn thành: ";
            case "title" -> "Công việc: ";
            case "desc" -> "Mô tả: ";
            case "input-command" -> "Nhập lệnh: ";
            case "list command" -> "Danh sách lệnh";
            case "next-button" -> "Trang kế";
            case "prev-button" -> "Trang trước";
            case "set-username-button" -> "Đổi tên";
            case "add-button" -> "Thêm";
            case "delete-button" -> "Xóa";
            case "detail-button" -> "Chi tiết";
            case "set-name" -> "Tên mới";
            case "unknown-command" -> "Không rõ lệnh gõ 'help' để xem danh lệnh";
            case "pause" -> "Nhấn Enter để tiếp tục";
            case "open-button" -> "Nạp file";
            case "save-button" -> "Xuất file";
            case "path-to-file" -> "Nhập đường dẫn";
            case "not-file" -> "Đường dẫn tới file không hợp lệ";
            case "warning-overwrite-data" -> "Bạn có muốn ghi đè dữ liệu lên file hiện tại?";
            case "warning-load-data" -> "Bạn có muốn ghi đè dữ liệu phiên hiện tại từ file?";
            case "type-yes-to-continue" -> "Gõ 'yes' để tiếp tục";
            case "process-done" -> "Hoàn tất";
            case "subtask-incomplete" -> "Số subtask chưa xong: ";
            case "toggle-button" -> "Đổi trạng thái ";
            case "subtask-total" -> "Nhiệm vụ con: ";
            case "set-lang-button" -> "Đổi ngôn ngữ ";
            case "done list-button" -> "Danh sách đã hoàn tất ";
            case "undone list-button" -> "Danh sách đang chờ ";
            case "exit-button" -> "Thoát ";
            case "done-list-name" -> "Danh sách việc đã hoàn thành của ";
            case "back-button" -> "Quay lại";
            case "index" -> "Nhập số thứ tự: ";
            case "-" -> "--------------------------------";
            case "create-sub" -> "Tạo nhiệm vụ con mới";
            case "create-task" -> "Tạo nhiệm vụ mới";
            case "placeholder-title" -> "Tên nhiệm vụ: ";
            case "placeholder-desc" -> "Mô tả: ";
            case "placeholder-deadline" -> "Hạn chót: ";
            case "placeholder-priority" -> "Mức ưu tiên (số nguyên): ";
            case "priority" -> "Mức ưu tiên: ";
            case "last-done" -> "Nhiệm vụ vừa hoàn thành";
            case "add-subtask-button" -> "Thêm nhiệm vụ con";
            case "toggle-this-button" -> "Đổi trạng thái nhv này";
            case "delete-this-button" -> "Xóa nhiệm vụ này";
            case "edit-this-button" -> "Sửa nhiệm vụ này";
            case "mother-task-name" -> "Nhiệm vụ chính: ";
            case "task-name" -> "Tiêu đề: ";
            case "deadline-time" -> "Hạn: ";
            case "done-sub-list" -> "Danh sách việc con đã hoàn thành";
            case "edit-task" -> "Sửa nhiệm vụ";
            case "subtask-remaining" -> "Nhiệm vụ con đang chờ";
            case "undone-sub-list" -> "Danh sách việc con đang chờ";
            case "tasks" -> "Nhiệm vụ";
            case "subtasks" -> "Nhiệm vụ con";
            case "refresh" -> "Làm mới";
            case "old-value" -> "Giá trị cũ: ";
            case "ok" -> "Đồng ý";
            case "cancel" -> "Hủy";
            case "path" -> "Đường dẫn:";
            case "changepath" -> "Đổi file";
            case "error" -> "Lỗi ";
            case "click-to-show" -> "Ấn để hiện ";
            case "hour" -> "giờ";
            case "minute" -> "phút";
            case "second" -> "giây";
            case "done-button" -> "Xong";
            case "undone-button" -> "Hủy";
            case "start-button" -> "Mở";
            case "page" -> "Trang ";
            case "subtask-completed" -> "Nhệm vụ con đã hoàn tất";
            default -> "Không xác định";
        };
    }

    @Override
    public String toString() {
        return "Tiếng Việt";
    }
}
