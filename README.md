# FastJoin — Fabric Mod (Minecraft 26.2)

Mod client tối ưu khi vào server: chuẩn bị sẵn kết nối mạng với buffer
socket lớn hơn để tải dữ liệu thế giới (chunk) nhanh hơn khi join.

## Sự thật cần biết trước khi dùng

Vanilla Minecraft **đã** bật `TCP_NODELAY` từ lâu, nên một mod client
không thể "tăng tốc kết nối" theo nghĩa giảm ping tới server — ping phụ
thuộc vào đường truyền mạng và vị trí server, không phải thứ client mod
kiểm soát được. Những gì mod này thực sự làm được:

1. Tăng buffer socket (SO_RCVBUF/SO_SNDBUF) để xử lý dữ liệu chunk lớn
   nhanh hơn khi vừa join.
2. Là khung sẵn để bạn thêm các tối ưu khác (VD: giảm số chunk cần tải
   trước khi hiện màn hình chơi, tương tự cách các mod hiệu năng như
   Lithium/Sodium làm ở phía render).

## Build

```bash
./gradlew build
```

File `.jar` xuất ra ở `build/libs/fastjoin-1.0.0.jar`. Copy vào thư mục
`mods` của bản cài Fabric Loader 0.19.3 cho Minecraft 26.2.

## Trước khi build lần đầu

Mở `gradle.properties` và kiểm tra lại các giá trị `yarn_mappings` và
`fabric_version` tại https://fabricmc.net/develop — vì các số build cụ
thể (VD: `26.2+build.1`) thay đổi liên tục theo từng bản phát hành nhỏ,
mình đã điền giá trị hợp lý nhất tại thời điểm này nhưng bạn nên xác
nhận lại số build mới nhất trước khi build để tránh lỗi dependency.

Nếu Mixin báo lỗi "target method not found" ở `ClientConnectionMixin`,
xem ghi chú trong file đó — cần đối chiếu tên method chính xác qua
Yarn mappings viewer (Linkie) cho bản 26.2.
