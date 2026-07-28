local key = KEYS[1]
local value = ARGV[1]
redis.set(key, value)

[[Writing from a read replica using EVAL command]]
[[
127.0.0.1:6379> EVAL "return redis.call('set', 'siddharth', 10)" 0
(error) READONLY You can't write against a read only replica. script: 51761a4ab7a099bff3b7d11231bc26096e01f162, on @user_script:1.
]]
[[Writing using EVAL_RO command]]
[[127.0.0.1:6379> EVAL_RO "return redis.call('set', 'siddharth', 20)" 0
(error) ERR Write commands are not allowed from read-only scripts. script: ee900cdfe13daa0a6ddbd0713f05674ecf5a00d8, on @user_script:1.]]

[[Using a redis-sdk for python and trying to set a key and value in redis]]
[[Traceback (most recent call last):
  File "/home/ubuntu/redisscripting.py", line 4, in <module>
    client.set('siddharth', 30)
    ~~~~~~~~~~^^^^^^^^^^^^^^^^^
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/utils.py", line 523, in wrapper
    return func(*args, **kwargs)
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/commands/core.py", line 4315, in set
    return self.execute_command("SET", *pieces, **options)
           ~~~~~~~~~~~~~~~~~~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/client.py", line 798, in execute_command
    return self._execute_command(*args, **options)
           ~~~~~~~~~~~~~~~~~~~~~^^^^^^^^^^^^^^^^^^
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/client.py", line 820, in _execute_command
    result = conn.retry.call_with_retry(
        lambda: self._send_command_parse_response(
    ...<3 lines>...
        with_failure_count=True,
    )
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/retry.py", line 120, in call_with_retry
    return do()
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/client.py", line 821, in <lambda>
    lambda: self._send_command_parse_response(
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^
        conn, command_name, *args, **options
        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    ),
    ^
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/client.py", line 764, in _send_command_parse_response
    return self.parse_response(conn, command_name, **options)
           ~~~~~~~~~~~~~~~~~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/client.py", line 864, in parse_response
    response = connection.read_response()
  File "/home/ubuntu/.venv/lib/python3.14/site-packages/redis/connection.py", line 1402, in read_response
    raise response
redis.exceptions.ReadOnlyError: You can't write against a read only replica]]