k = 5  # number of scribes
# number of pages per book. 11 pages for first book, 1 for second, etc.
pages = [10,4,9,3,11,7,2,22,15,6]

m = len(pages)  # number of books


def find_score(assignment):
    max_pages = -1
    for scribe in assignment:
        max_pages = max(max_pages, sum([pages[book] for book in scribe]))
    return max_pages


def find_assignment(assignment, scribe, book):
    if book == m:
        return find_score(assignment), assignment
    assign_current = [x[:] for x in assignment]  # deep copy
    assign_current[scribe].append(book)
    current = find_assignment(assign_current, scribe, book + 1)
    if scribe == k - 1:
        return current
    assign_next = [x[:] for x in assignment]  # deep copy
    assign_next[scribe + 1].append(book)
    next = find_assignment(assign_next, scribe + 1, book + 1)
    return min(current, next)


initial_assignment = [[] for x in range(k)]
print (find_assignment(initial_assignment, 0, 0))