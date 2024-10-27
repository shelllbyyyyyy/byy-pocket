/* eslint-disable react/no-unescaped-entities */
"use client";

import Image from "next/image";
import Link from "next/link";
import React, { useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useRouter } from "next/navigation";

import { Button } from "@/components/ui/button";
import CustomFormField, { FormFieldType } from "@/components/ui/custom-form";
import { Form } from "@/components/ui/form";
import Ripple from "@/components/ui/ripple";
import SubmitButton from "@/components/ui/submit-button";

import { loginFormSchema, LoginFormSchema } from "@/types/validations/auth";

import SignInWithGoogleButton from "../_components/sign-in-with-google-button";

export const LoginForm = () => {
  const router = useRouter();
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const form = useForm<LoginFormSchema>({
    resolver: zodResolver(loginFormSchema),
    defaultValues: {
      email: "",
      password: "",
    },
    reValidateMode: "onChange",
  });

  const signInWithPassword: SubmitHandler<LoginFormSchema> = async (value) => {
    const baseUrl = process.env.NEXT_PUBLIC_BASE_API_URL;

    try {
      setIsLoading(!isLoading);
      const result = await fetch(`${baseUrl}/auth/login`, {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: value.email,
          password: value.password,
        }),
      });

      const res = await result.json();

      if (res.code !== 200) {
        throw res.error;
      }

      router.push("/");
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoading(!!isLoading);
    }
  };

  return (
    <div className="flex max-sm:flex-col w-full justify-between overflow-hidden">
      <section
        id="sign-in"
        className="w-1/2 flex flex-col gap-5 px-1 relative max-sm:w-full"
      >
        <Ripple />
        <h2 className="text-4xl font-bold">
          <span className="text-main">Welcome</span> Back
        </h2>
        <h4>Please enter your credentials</h4>
        <Form {...form}>
          <form
            className="flex flex-col gap-5"
            onSubmit={form.handleSubmit(signInWithPassword)}
          >
            <CustomFormField
              className="rounded-md py-5"
              fieldType={FormFieldType.INPUT}
              control={form.control}
              name="email"
              label="Email"
              type="email"
              placeholder="example@email.com"
            />

            <CustomFormField
              className="rounded-md py-5"
              fieldType={FormFieldType.INPUT}
              control={form.control}
              name="password"
              label="Password"
              type="password"
              placeholder="********"
            />

            <div className="flex justify-between text-center items-center">
              <CustomFormField
                fieldType={FormFieldType.CHECKBOX}
                control={form.control}
                name="checkbox"
                label="Remember me"
              />
              <Button variant={"link"}>
                <Link href={"/auth/forgot-password"}>Forgot password ?</Link>
              </Button>
            </div>
            <div className="space-y-3 text-center">
              <SubmitButton
                className="w-full rounded-md py-5"
                type="submit"
                isLoading={isLoading}
              >
                Sign In
              </SubmitButton>
            </div>
          </form>
        </Form>
        <div className="space-y-3 text-center">
          <SignInWithGoogleButton />
          <Button variant={"link"}>
            <Link href={"/auth/register"}>
              Don't have an account ?{" "}
              <span className="text-secondary">sign up for free</span>
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
