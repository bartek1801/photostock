
1. Imię i nazwisko pracownika, który ma nazwisko na literę A, który został najwcześniej zatrudniony.
SELECT first_name, last_name
FROM employees
WHERE last_name LIKE 'A%'
ORDER BY hire_date ASC
LIMIT 1;

2. Ilość pracowników każdej płci.
SELECT  gender, COUNT(*) AS count_of_employees
FROM employees
GROUP BY gender;

3. Historię zarobków pracownika Georgi Facello.
SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name, s.salary, s.from_date, s.to_date
FROM  employees e
JOIN salaries s ON s.emp_no = e.emp_no
WHERE e.first_name = 'Georgi' AND e.last_name = 'Facello'
ORDER BY s.from_date;

SELECT e.*, s.*
FROM  employees e
JOIN salaries s ON s.emp_no = e.emp_no
ORDER BY e.hire_date DESC
LIMIT 5;

SELECT salaries.*
FROM salaries
JOIN employees  ON employees.emp_no = salaries.emp_no
WHERE employees.first_name = 'Georgi' AND employees.last_name = 'Facello'

4. Najczęściej powtarzające się imię wśród pracowników.
SELECT first_name, COUNT(first_name) AS count_of_employees
FROM employees
GROUP BY first_name
ORDER BY count_of_employees DESC
LIMIT 1;

5. Pierwszych 5 pracowników, którzy zarabiają najwięcej.
SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name, s.salary
FROM employees e
JOIN salaries s ON s.emp_no = e.emp_no
WHERE s.to_date = '9999-01-01'
GROUP BY employee_name
ORDER BY s.salary DESC LIMIT 5;

SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name, s.salary
FROM employees e
JOIN salaries s ON s.emp_no = e.emp_no
WHERE s.to_date > NOW()
ORDER BY s.salary DESC
LIMIT 5;


6. 5 najpóźniej zatrudnionych pracowników oraz ich bieżący dział.
SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name, hire_date, d.dept_name
FROM employees e
JOIN dept_emp de ON de.emp_no = e.emp_no
JOIN departments d ON d.dept_no = de.dept_no
WHERE de.to_date > NOW()
ORDER BY e.hire_date DESC
LIMIT 5;

7. Działy oraz ich bieżących kierowników.
SELECT  d.dept_name, CONCAT(e.first_name, ' ', e.last_name) AS manager_name
FROM departments d
JOIN dept_emp de ON de.dept_no = d.dept_no
JOIN dept_manager dm ON dm.dept_no = de.dept_no
JOIN employees e ON e.emp_no = dm.emp_no
WHERE dm.to_date > NOW()
ORDER BY d.dept_name;


8. 5 najwcześniej zatrudnionych pracowników wraz z ich bieżącym tytułem, działem oraz zarobkami.
SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name, e.hire_date, t.title, s.salary
FROM employees e
JOIN titles t ON e.emp_no = t.emp_no
JOIN salaries s ON e.emp_no = s.emp_no
WHERE t.to_date > NOW() AND s.to_date > NOW()
ORDER BY e.hire_date, employee_name
LIMIT 5;

SELECT
  e.emp_no,

  e.hire_date,
  t.title,
  s.salary
FROM employees e
;





9. Działy wraz ze średnimi zarobkami, największymi zarobkami, najmniejszymi zarobkami i ilością pracowników. Chodzi o bieżące zarobki i bieżącą ilość pracowników.
SELECT  d.dept_name, AVG(s.salary), MAX(s.salary), MIN(s.salary), COUNT(e.emp_no)
FROM departments d
JOIN dept_emp de ON de.dept_no = d.dept_no
JOIN employees e ON e.emp_no = de.emp_no
JOIN salaries s ON s.emp_no = e.emp_no
WHERE s.to_date > NOW() AND de.to_date > NOW()
GROUP BY d.dept_name

10. Średnią bieżącą pensję kierowników działów.
SELECT  AVG(s.salary)
FROM dept_manager dm
JOIN salaries s ON s.emp_no = dm.emp_no
WHERE dm.to_date > NOW()


11. Imię i nazwisko pracownika, który ma największą różnicę w pensji początkowej i bieżącej.
SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
  ABS(
  (SELECT  salary
    FROM salaries
    WHERE salaries.emp_no = e.emp_no
      ORDER BY from_date ASC
    LIMIT 1)
   -
  (SELECT  salary
   FROM salaries
   WHERE salaries.emp_no = e.emp_no
   ORDER BY from_date DESC
   LIMIT 1 ))
    AS diff
FROM employees e
JOIN dept_emp ON e.emp_no = dept_emp_no AND demp_emp.to_date > NOW()
ORDER BY diff
LIMIT 1
;

SELECT concat('d', LPAD(substr(dept_no, 2) + 1, 3, '0'))
FROM departments
ORDER BY dept_no
DESC LIMIT 1