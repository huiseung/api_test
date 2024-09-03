import re
from collections import Counter


def parse_log_file(file_path):
    port_counter = Counter()

    with open(file_path, 'r') as file:
        for line in file:
            match = re.search(r'Local port used for request: (\d+)', line)
            if match:
                port = match.group(1)
                port_counter[port] += 1

    return dict(port_counter)


# 파일 경로를 지정하세요
file_path = './info.log'

result = parse_log_file(file_path)
print("key: ", len(result.keys()))

# 결과 출력을 좀 더 보기 좋게 만들기
for port, count in sorted(result.items(), key=lambda x: int(x[0])):
    print(f"Port {port}: {count} times")