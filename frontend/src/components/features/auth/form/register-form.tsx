/* eslint-disable react/no-unescaped-entities */
"use client";

import Image from "next/image";
import Link from "next/link";
import { useRouter } from "next/navigation";
import React, { useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

import { Button } from "@/components/ui/button";
import CustomFormField, { FormFieldType } from "@/components/ui/custom-form";
import { Form } from "@/components/ui/form";
import SubmitButton from "@/components/ui/submit-button";

import {
  RegisterFormSchema,
  registerFormSchema,
} from "@/types/validations/auth";
import Ripple from "@/components/ui/ripple";
import SignInWithGoogleButton from "../_components/sign-in-with-google-button";

export const RegisterForm = () => {
  const router = useRouter();
  const [isChecked, setIsChecked] = useState(false);

  const form = useForm<RegisterFormSchema>({
    resolver: zodResolver(registerFormSchema),
    defaultValues: {
      username: "",
      email: "",
      password: "",
    },
    reValidateMode: "onChange",
  });

  const signUp: SubmitHandler<RegisterFormSchema> = async (value) => {
    const baseUrl = process.env.NEXT_PUBLIC_BASE_API_URL;
    try {
      setIsChecked(!isChecked);

      const result = await fetch(`${baseUrl}/auth/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: value.username,
          email: value.email,
          password: value.password,
        }),
      });

      const res = await result.json();

      if (res.code != 201) {
        throw res.error;
      }

      router.push(`/auth/send-verification/${value.email}`);
    } catch (error) {
      console.log(error);
    } finally {
      setIsChecked(!!isChecked);
    }
  };

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  return (
    <div className="flex max-sm:flex-col w-full justify-between overflow-hidden">
      <section
        id="sign-in"
        className="w-1/2 flex flex-col gap-5 px-1 relative max-sm:w-full"
      >
        <Ripple />
        <h2 className="text-4xl font-bold">
          <span className="text-main">Hi</span> There
        </h2>
        <h4>Please register first to continue</h4>
        <Form {...form}>
          <form
            className="flex flex-col gap-5"
            onSubmit={form.handleSubmit(signUp)}
          >
            <CustomFormField
              className="py-5 rounded-md"
              fieldType={FormFieldType.INPUT}
              control={form.control}
              name="username"
              label="Username"
              type="username"
              placeholder="john doe"
            />

            <CustomFormField
              className="py-5 rounded-md"
              fieldType={FormFieldType.INPUT}
              control={form.control}
              name="email"
              label="Email"
              type="email"
              placeholder="example@email.com"
            />

            <CustomFormField
              className="py-5 rounded-md"
              fieldType={FormFieldType.INPUT}
              control={form.control}
              name="password"
              label="Password"
              type="password"
              placeholder="********"
            />
            <CustomFormField
              control={form.control}
              fieldType={FormFieldType.CHECKBOX}
              name="eek"
              label={
                <p>
                  By clicking register, I agree with the{" "}
                  <span className="text-primary">Terms and Conditions </span>
                </p>
              }
              onClick={handleCheckboxChange}
            />
            <div className="space-y-3 text-center">
              <SubmitButton
                isLoading={isChecked ? false : true}
                className="w-full rounded-md py-5"
                type="submit"
              >
                Register an account
              </SubmitButton>
            </div>
          </form>
        </Form>
        <div className="space-y-3 text-center">
          <SignInWithGoogleButton />
          <Button variant={"link"}>
            <Link href={"/auth/login"}>
              Already have an account ?{" "}
              <span className="text-red-600">sign in</span>
            </Link>
          </Button>
        </div>
      </section>
      <figure className="w-1/2 flex place-content-end max-sm:hidden">
        <Image
          alt="logo"
          src="/assets/auth.png"
          width={540}
          height={650}
          priority
          className="object-cover"
        />
      </figure>
    </div>
  );
};
