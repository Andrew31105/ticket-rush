-- KEYS[1]: Redis Key của sự kiện (vd: event_ticket:101)
-- ARGV[1]: Hành động ("BOOK" hoặc "CANCEL")
-- ARGV[2]: Số lượng vé (thường là "1")

local ticket_key = KEYS[1]
local action = ARGV[1]
local amount = tonumber(ARGV[2])

-- 1. Lấy số lượng hiện tại
local current_stock = redis.call('get', ticket_key)

-- 2. Kiểm tra nếu Key không tồn tại (Tránh tạo key rác nếu chưa nạp từ DB)
if not current_stock then
    return -1
end

current_stock = tonumber(current_stock)

-- 3. Xử lý logic theo hành động
if action == "BOOK" then
    -- Kiểm tra còn đủ vé không
    if current_stock >= amount then
        local new_stock = redis.call('decrby', ticket_key, amount)
        return new_stock -- Trả về số lượng còn lại (>= 0)
    else
        return -2 -- Mã lỗi: Hết vé (Sold out)
    end

elseif action == "CANCEL" then
    -- Tăng lại số lượng
    local new_stock = redis.call('incrby', ticket_key, amount)
    return new_stock -- Trả về số lượng sau khi hồi lại

else
    -- Hành động không hợp lệ (Ví dụ truyền nhầm tham số)
    return -3
end