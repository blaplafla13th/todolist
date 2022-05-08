package blaplafla.todolist.models.dictionary;

public class Vietnamese extends Dictionary {
    @Override
    public String errorExplain(int errorCode) {
        return switch (errorCode) {
            case 1 -> "Thành công";
            case 2 -> "Không tìm thấy file";
            case 3 -> "Không thể đọc ghi file";
            case 4 -> "Dữ liệu không hợp lệ";
            default -> "Không xác định";
        };
    }

    @Override
    public String prettyTime(long day, long hour, long minute, long second) {
        return String.format("còn lại %d ngày %d giờ %d phút %d giây", day, hour, minute, second);
    }

    @Override
    public String label(String label) {
        return switch (label){
            case "todolist-name" -> "Danh sách việc cần làm của ";
            default -> "Không xác định";
        };
    }
}
