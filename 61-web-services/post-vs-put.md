# POST vs PUT

https://stackoverflow.com/questions/630453/put-vs-post-in-rest

considerations:

- Do you name your URL objects you create explicitly, or let the server decide? If you name them then use PUT. 
If you let the server decide then use POST.

- PUT is idempotent, so if you PUT an object twice, it has no effect. This is a nice property, 
so I would use PUT when possible.

- You can update or create a resource with PUT with the same object URL

- With POST you can have 2 requests coming in at the same time making modifications to a URL, 
and they may update different parts of the object.