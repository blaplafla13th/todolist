package blaplafla.todolist.models.dictionary;

public class Vietnamese extends Dictionary {
    @Override
    public String errorExplain(int errorCode) {
        return switch (errorCode) {
            case 100 -> "Thành công";
            case 200 -> "Không tìm thấy file dữ liệu";
            case 201 -> "Không tìm thấy file ngôn ngữ";
            case 300 -> "Không thể đọc ghi file dữ liệu";
            case 301 -> "Không thể đọc ghi file ngôn ngữ";
            case 400 -> "Dữ liệu của tệp không hợp lệ";
            case 401 -> "Dữ liệu của tệp ngôn ngữ không hợp lệ";
            case 500 -> "Lỗi nhập liệu người dùng";
            case 501 -> "Không tìm thấy id được cung cấp";
            case 502 -> "Ngày không hợp lệ";
            case 503 -> "Thời gian không hợp lệ";
            default -> "Không xác định";
        };
    }

    @Override
    public String prettyTime(long day, long hour, long minute, long second) {
        return String.format("còn lại %d ngày %d giờ %d phút %d giây", day, hour, minute, second);
    }

    @Override
    public String label(String label) {
        return switch (label) {
            case "todolist-name" -> "Danh sách việc cần làm của ";
            case "current-lang" -> "Ngôn ngữ hiện tại: ";
            case "set-lang" -> "Đổi ngôn ngữ sang: ";
            case "set-lang-term" -> "Nhập số của lựa chọn: ";
            case "def" -> "Trả về giá trị mặc định: ";
            case "input-date" -> "Nhập giá trị ngày (định dạng năm-tháng-ngày:) ";
            case "input-time" -> "Nhập giá trị thời gian (định dạng giờ:phút:giây (24h):) ";
            default -> "Không xác định";
        };
    }

    @Override
    public String getInfo() {
        return "Tiếng Việt";
    }
}
