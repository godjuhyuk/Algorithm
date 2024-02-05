import sys
def dot(a):
    x, y = map(int, a.split())
    return y+ 0.000001*x

print(''.join(sorted(sys.stdin.readlines()[1:], key=lambda x: dot(x))))
    