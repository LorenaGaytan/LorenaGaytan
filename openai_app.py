from io import StringIO
from pdfminer.high_level import extract_text_to_fp

output_string = StringIO()
#with open('C:/Users/ruela/Downloads/GG197439-48934753-043955.pdf', 'rb') as fin:
with open('C:/Users/ruela/Downloads/EGO_LORE.pdf', 'rb') as fin:
       extract_text_to_fp(fin, output_string)
data = output_string.getvalue()

system_msg = "You are an blood test analyst expert."
query = """
Get patient name
Classify this document and return one of these two document types as response - [Coprológico, EGO]
Show value Leukocytes by field 
Return patient name, test type and Leukocytes per field in the response.

Patient Name =
Document Type = 
Leukocytes per field =

"""
user_msg = data + "\n\n" + query

response = openai.ChatCompletion.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": system_msg},
        {"role": "user", "content": user_msg}
    ]
)
print(response["choices"][0]["message"]["content"])