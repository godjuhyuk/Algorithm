ans = []
for _ in range(9):
    ans.append(int(input()))
print(max(ans), ans.index(max(ans))+1)