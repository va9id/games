import curses
from random import randint

WIDTH = 60
HEIGHT = 20

curses.initscr()
if (not curses.has_colors()):
    exit(1)

curses.start_color()
win = curses.newwin(HEIGHT, WIDTH, 0, 0)  # rows, columns
win.keypad(1)
curses.noecho()
curses.curs_set(0)
win.border(0)
win.nodelay(1)  # -1
snake = [(4, 4), (4, 3), (4, 2)]
food = (randint(1, HEIGHT - 2), randint(1, WIDTH - 2))
win.addch(food[0], food[1], '\u2588', curses.color_pair(3))
# game logic
score = 0

ESC = 27
key = curses.KEY_RIGHT

# Colours
curses.start_color()
curses.init_pair(1, curses.COLOR_WHITE, curses.COLOR_BLACK)
curses.init_pair(2, curses.COLOR_GREEN, curses.COLOR_BLACK)
curses.init_pair(3, curses.COLOR_RED, curses.COLOR_BLACK)
while key != ESC:
    win.addstr(0, 2, 'Score ' + str(score) + ' ')
    win.timeout(150 - (len(snake)) // 5 + len((snake))//10 %
                120)  # increase speed

    prev_key = key
    event = win.getch()
    key = event if event != -1 else prev_key

    if key not in [curses.KEY_LEFT, curses.KEY_RIGHT, curses.KEY_UP, curses.KEY_DOWN, ESC, 454, 456, 450, 452]:
        key = prev_key

    # calculate the next coordinates
    y = snake[0][0]
    x = snake[0][1]
    if key == curses.KEY_DOWN or key == 456:
        y += 1
    if key == curses.KEY_UP or key == 450:
        y -= 1
    if key == curses.KEY_LEFT or key == 452:
        x -= 1
    if key == curses.KEY_RIGHT or key == 454:
        x += 1

    snake.insert(0, (y, x))  # append O(n)

    # check if we hit the border
    if y == 0:
        break
    if y == HEIGHT - 1:
        break
    if x == 0:
        break
    if x == WIDTH - 1:
        break

    # if snake runs over itself
    if snake[0] in snake[1:]:
        break
    if snake[0] == food:
        # eat the food
        score += 1
        food = ()
        while food == ():
            food = (randint(1, HEIGHT-2), randint(1, WIDTH - 2))
            if food in snake:
                food = ()
        win.addch(food[0], food[1], '\u2588', curses.color_pair(3))
    else:
        # move snake
        last = snake.pop()
        win.addch(last[0], last[1], ' ')

    win.addch(snake[0][0], snake[0][1], '0', curses.color_pair(2))
    # win.refresh()

curses.endwin()
print(f"\033 Final score = {score}")
