FROM python:3.6-slim

WORKDIR /load

COPY requirements.txt /load/

RUN pip install -r requirements.txt

COPY entrypoint.sh /load/
COPY load-test.py /load/

CMD ["./entrypoint.sh"]
