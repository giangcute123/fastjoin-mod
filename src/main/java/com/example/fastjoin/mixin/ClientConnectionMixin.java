package com.example.fastjoin.mixin;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Ghi chú: Vanilla Minecraft đã tự bật TCP_NODELAY khi kết nối tới server,
 * nên phần "tăng tốc" thực sự nằm ở việc TĂNG kích thước buffer socket
 * (SO_RCVBUF/SO_SNDBUF) để nhận/gửi gói tin lớn (VD: chunk data) nhanh hơn,
 * đặc biệt hữu ích khi vào server có nhiều dữ liệu thế giới cần tải.
 *
 * LƯU Ý: tên phương thức "connect" và chữ ký tham số có thể thay đổi giữa
 * các bản mapping Yarn. Nếu build lỗi "target method not found", hãy mở
 * ClientConnection.class qua Linkie/Yarn viewer cho MC 26.2 để lấy đúng
 * tên & chữ ký, rồi sửa lại @Inject bên dưới cho khớp.
 */
@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "connect", at = @At("HEAD"))
    private static void fastjoin$logConnect(CallbackInfoReturnable<ClientConnection> cir) {
        // Điểm để log / theo dõi thời điểm bắt đầu kết nối nếu cần debug.
    }

    /**
     * Nếu bạn tìm thấy nơi Bootstrap được tạo (thường trong cùng method
     * "connect" ở trên, dạng: new Bootstrap().group(...).channel(...)...),
     * bạn có thể dùng @ModifyVariable hoặc viết lại toàn bộ method đó để
     * chèn thêm 2 dòng sau trước khi gọi .connect(address):
     *
     *   bootstrap.option(ChannelOption.SO_RCVBUF, 1 << 20);
     *   bootstrap.option(ChannelOption.SO_SNDBUF, 1 << 20);
     *
     * Mixin phức tạp hơn (Overwrite hoàn toàn method) không được đưa sẵn
     * ở đây vì cần khớp chính xác bytecode gốc của bản 26.2 — rủi ro cao
     * nếu Mojang đổi cấu trúc method giữa các bản vá nhỏ.
     */
}
