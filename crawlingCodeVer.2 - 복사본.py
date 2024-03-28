from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import time
import csv

# 크롬 옵션 설정
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)
chrome_options.add_experimental_option("excludeSwitches", ["enable-logging"])
chrome_options.add_argument('--ignore-certificate-errors')

# 크롬 WebDriver 설정
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=chrome_options)


try:
    # 데이터를 저장할 리스트
    gpu_data = []

    # 사이트로 이동
    driver.get("https://www.videocardbenchmark.net/gpu_list.php")

    # 페이지가 로드될 때까지 기다림
    WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "tbody > tr"))
    )

    # GPU 데이터 추출
    gpu_rows = driver.find_elements(By.CSS_SELECTOR, "tbody > tr")

    # 리스트가 비어 있는지 확인
    if not gpu_rows:
        print("GPU 정보를 찾을 수 없습니다.")
        

    # 각 <tr> 태그에서 데이터 추출
    for row in gpu_rows:
        columns = row.find_elements(By.TAG_NAME, "td")

        # columns 리스트가 예상한 길이인지 확인
        if len(columns) < 5:
            print("추출된 데이터가 부족합니다.")
            continue

        gpu_data.append({
            "Name": columns[0].text,
            "PassMark Score": columns[1].text,
            "Rank": columns[2].text,
            "Value": columns[3].text,
            "Price": columns[4].text
        })
        print(gpu_data)

    # CSV 파일에 데이터 쓰기
    with open('gpu_data.csv', 'w', newline='', encoding='utf-8') as file:
        writer = csv.DictWriter(file, fieldnames=["Name", "PassMark Score", "Rank", "Value", "Price"])
        writer.writeheader()
        for row in gpu_data:
            writer.writerow(row)

except Exception as e:
    print(f"오류 발생: {e}")
finally:
    # WebDriver 종료
    driver.quit()
