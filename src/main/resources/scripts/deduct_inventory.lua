-- KEYS[1]: key của event (vd: event_tickets:101)
-- ARGV[1]: số lượng vé muốn mua (thường là 1)
local ticket_key = KEYS[1]
local amount = tonumber(ARGV[1])
local current_stock = tonumber(redis.call('get', ticket_key) or "0")

if current_stock >= amount then
    redis.call('decrby', ticket_key, amount)
    return 1
else
    return 0
end