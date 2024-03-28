# pip install selenium
# pip install webdriver_manager 
# 이두개 다운받고 실행하기.

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
import time
import csv
import matplotlib.pyplot as plt
import numpy as np

# 크롬 옵션 설정
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)
chrome_options.add_experimental_option("excludeSwitches", ["enable-logging"])

# 크롬 WebDriver 설정
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

# 스크랩할 페이지 수 지정
start_page = 1
end_page = 2  # 스크랩할 마지막 페이지 번호로 바꿔주세요
# 데이터를 저장할 리스트
data = []

# 각 페이지를 순회
for page in range(start_page, end_page + 1):
    # 해당 페이지로 이동
    driver.get(f"https://www.newegg.ca/p/pl?d=gpu&page={page}&Order=1")
    time.sleep(3)  # 페이지 로딩을 위해 잠시 대기

    # item-title과 price-current 클래스 이름을 가진 요소들 찾기
    items = driver.find_elements(By.CLASS_NAME, "item-title")
    prices = driver.find_elements(By.CLASS_NAME, "price-current")

    # 요소들로부터 텍스트 추출
    for item, price in zip(items, prices):
        item_title = item.text
        price_current = price.text
        print(f"페이지 {page} - 제품: {item_title}, 가격: {price_current}")
        # 소수점을 제외한 비숫자 문자 제거
        cleaned_price = ''.join(char for char in price_current if char.isdigit() or char == '.')
        try:
            price_float = float(cleaned_price)  # float형으로 변환
            data.append({"title": item_title, "price": price_float})
        except ValueError:
            print("error")
        print(f"제품: {item_title}, 가격 변환 불가: {cleaned_price}")

# 가격순으로 데이터 정렬
sorted_data = sorted(data, key=lambda x: x["price"])
# 데이터에서 가격만 추출
prices = [item['price'] for item in sorted_data]

# 평균, 중앙값, 표준편차, 분산 계산
average_price = np.mean(prices)
median_price = np.median(prices)
std_dev = np.std(prices)
variance = np.var(prices)

# 결과 출력
average_price, median_price, std_dev, variance
# CSV 파일에 데이터 쓰기
with open('gpu_prices.csv', 'w', newline='', encoding='utf-8') as file:
    writer = csv.DictWriter(file, fieldnames=["title", "price"])
    writer.writeheader()
    for row in sorted_data:
        writer.writerow(row)

# WebDriver 종료
driver.quit()

# 더 이상 필요하지 않으면 WebDriver 종료
driver.quit()
